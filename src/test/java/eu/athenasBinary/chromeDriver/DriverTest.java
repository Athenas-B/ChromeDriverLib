package eu.athenasBinary.chromeDriver;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class DriverTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DriverTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(DriverTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        ChromeProperties chromeProperties = new ChromeProperties();
        ChromeDriverClass chromeDriverClass = new ChromeDriverClass(chromeProperties);

        assertNotNull(chromeDriverClass);
        assertNotNull(chromeDriverClass.driver);

        chromeDriverClass.driver.get("https://www.google.com/");
        String title = chromeDriverClass.driver.getTitle();

        String expected = "Google";

        assertEquals(expected, title);
    }
}
