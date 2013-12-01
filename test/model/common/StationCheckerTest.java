/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model.common;

import model.util.StationUtilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author skuarch
 */
public class StationCheckerTest {
    
    public StationCheckerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkStationSocket method, of class StationChecker.
     */
    @Test
    public void testCheckStationSocket() throws Exception {
        
        String host = StationUtilities.getIPAddress("http://204.45.73.122:8000");
        int port = 8000;
        int stopSeconds = 4000;
        int maxbytes = 512;
        
        //http://204.45.73.122:8000
        
        System.out.println(new StationChecker().checkStationSocket(host, port, stopSeconds, maxbytes));
        
    }

  
    
}
