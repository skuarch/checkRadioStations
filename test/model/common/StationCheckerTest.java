package model.common;

import model.util.StationUtilities;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

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
        
        System.out.println("socket");
        
        String host = StationUtilities.getIPAddress("http://184.154.177.106:8194");
        int port = StationUtilities.getPort("http://184.154.177.106:8194");        
        int stopSeconds = 4000;
        int maxbytes = 1024*15;
        
        //http://204.45.73.122:8000
        
        System.out.println(new StationChecker().checkStationSocket(host, port, stopSeconds, maxbytes));
        
    }

    /**
     * Test of checkStationUrl method, of class StationChecker.
     */
    @Ignore
    @Test
    public void testCheckStationUrl() throws Exception {
        
        System.out.println("url");
        
        System.out.println("checkStationUrl");
        String host = StationUtilities.getIPAddress("http://184.154.177.106:8194/");
        int stopSeconds = 0;
        int maxBytes = 0;
        StationChecker instance = new StationChecker();
        boolean expResult = false;
        boolean result = instance.checkStationUrl(host, stopSeconds, maxBytes);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

  
    
}
