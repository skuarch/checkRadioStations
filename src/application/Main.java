package application;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
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
    private static final int maxBytes = 1024 * 20;
    private static final int stopSeconds = 3500;
    private int port;
    private boolean isActive = false;
    private String ip;
    private ArrayList<Station> stations = null;

    //==========================================================================
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Main().execute();

    } // end Main

    //==========================================================================
    private void execute() {

        new Timer().scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                try {

                    stations = new DAO().getArrayList(new Station());

                    stations.stream().forEach((station) -> {
                        isActive = false;
                        isActive = checkStation(station);
                        updateStation(station, isActive);
                    });

                } catch (Exception e) {
                    logger.error("main", e);
                }

            }
            
        }, 60000, 60000);

    }// end execute

    //==========================================================================
    private static void updateStation(final Station station, final boolean isActive) {

        new Thread(() -> {

            try {

                if (isActive) {
                    station.setActive(1);
                } else {
                    station.setActive(0);
                }

                new DAO().update(station);

            } catch (Exception e) {
                logger.error("updateStation", e);
            }

        }).start();

    }

    //==========================================================================
    private synchronized boolean checkStation(final Station station) {

        port = StationUtilities.getPort(station.getUrl());

        if (port == -1) {

            try {
                isActive = new StationChecker().checkStationUrl(station.getUrl(), stopSeconds, maxBytes);
            } catch (Exception e) {
                isActive = false;
            }

        } else {
            ip = StationUtilities.getIPAddress(station.getUrl());

            try {
                isActive = new StationChecker().checkStationSocket(ip, port, stopSeconds, maxBytes);
            } catch (Exception e) {
                isActive = false;
            }

        }
        //System.out.println(station.getName() + " " + isActive);
        return isActive;

    } // end checkStation

} // end class
