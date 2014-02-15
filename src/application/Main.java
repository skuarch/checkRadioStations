package application;

import java.util.ArrayList;
import model.beans.Station;
import model.common.StationChecker;
import model.dao.DAO;
import model.util.StationUtilities;
import org.apache.log4j.Logger;

/**
 *
 * @author skuarch
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);
    private static int maxBytes = 1024 * 2;
    private static int stopSeconds = 3000;
    int port;
    boolean isActive = false;
    String ip;

    //==========================================================================
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Main().execute();

    } // end Main

    //==========================================================================
    private void execute() {

        ArrayList<Station> stations = null;

        try {

            stations = new DAO().getArrayList(new Station());

            for (final Station station : stations) {

                isActive = false;
                isActive = checkStation(station);

                if (isActive) {
                    station.setActive(1);
                    new DAO().update(station);
                } else {
                    station.setActive(0);                    
                }
                
                new DAO().update(station);

                System.out.println("is active " + isActive + " " + station.getName() + " " + station.getUrl());
                

            }

        } catch (Exception e) {
            logger.error("main", e);
        }

    }// end execute

    //==========================================================================
    private synchronized boolean checkStation(Station station) {

        try {

            port = StationUtilities.getPort(station.getUrl());

            if (port == -1) {
                isActive = new StationChecker().checkStationUrl(station.getUrl(), stopSeconds, maxBytes);
            } else {
                ip = StationUtilities.getIPAddress(station.getUrl());
                isActive = new StationChecker().checkStationSocket(ip, port, stopSeconds, maxBytes);
            }

        } catch (Exception e) {
            isActive = false;
        }

        return isActive;
    } // end checkStation

} // end class
