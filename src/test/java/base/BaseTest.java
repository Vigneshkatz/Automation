package base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.smytten.export.ExcelReportGenerator;
import org.smytten.util.driver.DriverHelper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertNotNull;

public class BaseTest {

    public AndroidDriver driver;
    public Map<String, String> testResults;
    public DriverHelper driverHelper;

    @BeforeSuite
    public void setUp() {
//        startAppiumServer();
        System.out.println("Appium server started successfully");
        try {
            driver = initializeDriver();
            this.driverHelper = new DriverHelper(driver);
            assertNotNull("Driver initialization failed", driver);
            long time = 10;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        } catch (Exception e) {
            System.err.println("Invalid Appium server URL: " + e.getMessage());
        }
    }

    @AfterSuite
    public void tearDown() {
        ExcelReportGenerator excelReportGenerator = new ExcelReportGenerator(testResults);
        excelReportGenerator.generateExcelReport();
        System.out.println("Appium server stopped");
        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit successfully");
        }
        stopAppiumServer();
    }

    private AndroidDriver initializeDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability(AppiumCapability.DEVICE_NAME.getCapabilityName(), "OnePlus LE2111");
//        caps.setCapability(AppiumCapability.PLATFORM_VERSION.getCapabilityName(), "14");
//        caps.setCapability(AppiumCapability.PLATFORM_NAME.getCapabilityName(), "Android");
//        caps.setCapability(AppiumCapability.AUTOMATION_NAME.getCapabilityName(), "UiAutomator2");
//        caps.setCapability(AppiumCapability.AUTO_GRANT_PERMISSIONS.getCapabilityName(), true);
//        caps.setCapability(AppiumCapability.ENFORCE_APP_INSTALL.getCapabilityName(), true);
//        caps.setCapability("appWaitForLaunch",false);
//        caps.setCapability(AppiumCapability.APP.getCapabilityName(), "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-debug (1).apk");
        // Set platformName to Android
        caps.setCapability("platformName", "Android");

        // Set deviceName to OnePlus LE2111
        caps.setCapability("deviceName", "OnePlus LE2111");

        // Set platformVersion to 14
        caps.setCapability("platformVersion", "14");

        // Set automationName to UiAutomator2
        caps.setCapability("automationName", "UiAutomator2");

        // Set autoGrantPermissions to true
        caps.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);

        // Set enforceAppInstall to true
        caps.setCapability("enforceAppInstall", true);

        // Set app path
        caps.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-debug (1).apk");
//        caps.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, Duration.ofSeconds(8));

        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
    }

    private void startAppiumServer() {
        try {
            Runtime.getRuntime().exec("appium");
        } catch (Exception e) {
            e.printStackTrace();
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
        } catch (IOException | InterruptedException e) {
            // Handle exceptions gracefully
            e.printStackTrace();
        }
    }

    public void recordResult(String methodName, String result) {
        if (testResults == null) {
            testResults = new HashMap<>();
        }
        testResults.put(methodName, result);
    }

    private String getCurrentDateTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        return currentDateTime.format(formatter);
    }

}
