package base;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.smytten.helper.AndroidHelper;
import org.smytten.helper.SmyttenHelper;
import org.smytten.naviation.AccountPageNavigation;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.AssertJUnit.assertNotNull;

public class BaseTest {

    private static final Logger LOGGER = Logger.getLogger(BaseTest.class.getName());
    private static final long implicitWaitTime = 10;
    public static AndroidDriver driver;
    public AndroidHelper androidHelper;
    public SmyttenHelper smyttenHelper;
    public AccountPageNavigation accountPageNavigation;
    public static void startFromLoginScreen() {
        driver.startActivity(new Activity("com.app.smytten.debug", "com.app.smytten.ui.auth.PreLoginActivity"));
    }

    @BeforeClass
    public void setUp() {
        LOGGER.info("Setting up test environment...");
        startAppiumServer();
        try {
            driver = initializeDriver();
            assertNotNull("Driver initialization failed", driver);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
            androidHelper = new AndroidHelper(driver);
            smyttenHelper = new SmyttenHelper(driver, androidHelper);
            accountPageNavigation = new AccountPageNavigation(driver, androidHelper);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Invalid Appium server URL: " + e.getMessage());
        }
    }

    @AfterSuite
    public void tearDown() {
        LOGGER.info("Tearing down test environment...");
        if (driver != null) {
            driver.quit();
            LOGGER.info("Driver quit successfully");
        }
        stopAppiumServer();
    }

    private AndroidDriver initializeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "OnePlus LE2111");
//        capabilities.setCapability("deviceName", "realme RMX1971");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("newCommandTimeout", 30000);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability("enforceAppInstall", true);
        capabilities.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-173-debug.apk");
        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    private void startAppiumServer() {
        try {
            Runtime.getRuntime().exec("appium");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to start Appium server: " + e.getMessage());
        }
    }

    private void stopAppiumServer() {
        try {
            ProcessBuilder builder = new ProcessBuilder("pkill", "-9", "node");
            Process process = builder.start();
            boolean completed = process.waitFor(5, TimeUnit.SECONDS);
            if (!completed) {
                process.destroy();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to stop Appium server: " + e.getMessage());
        }
    }
}
