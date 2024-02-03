package eu.athenasBinary.chromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ChromeDriverClass {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    protected ChromeProperties properties;

    public ChromeDriverClass(ChromeProperties chromeProperties) {
        this.properties = chromeProperties;
        intChromedriver();
    }

    protected void intChromedriver() {
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--start-maximized");
            if (!properties.isSandbox()) {
                chromeOptions.addArguments("--no-sandbox");
            }
            if (properties.isIncognito()) {
                chromeOptions.addArguments("--incognito");
            }
            chromeOptions.addArguments("--headless");
            if (!properties.isShowChrome()) {
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--kiosk");
            }
            chromeOptions.addArguments("--log-level=3");
            chromeOptions.addArguments("--window-size=1280,700");


            System.setProperty("webdriver.chrome.driver", "./chromedriver");
            chromeOptions.addArguments("--remote-allow-origins=*");

            driver = new ChromeDriver(chromeOptions);


            //chromeOptions.setBinary("/usr/bin/google-chrome");


            webDriverWait = new WebDriverWait(driver,
                    Duration.ofSeconds(properties.getDriverTimeoutSeconds()));
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
            //  MyLogger.debug(e);
        }
    }

    public void clickWait(final By clickLocator, final By waitLocator, boolean visibility) {
        driver.findElement(clickLocator).click();
        if (visibility) {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(waitLocator));
        }
        waitForLoad();
        if (!visibility) {
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(waitLocator));
        }
        waitForLoad();
    }

    public void waitForLoad() {
        //new WebDriverWait(driver, properties.getDriverTimeoutSeconds()).until(
        //      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public boolean isElementThere(final By elementLocator) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
        boolean exists = driver.findElements(elementLocator).size() != 0;
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return exists;
    }
}
