package signin;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertTrue;

public class LandingPageTest {
    private AndroidDriver driver;
    private WebDriverWait wait;
    @BeforeTest
    public void setUp() throws MalformedURLException, InterruptedException {
        startAppiumServer();
        DesiredCapabilities caps = new DesiredCapabilities();
//        oneplus
        caps.setCapability("deviceName", "OnePlus LE2111");
        caps.setCapability("platformVersion", "14");
        caps.setCapability("platformName", "Android");
//        realme
//        caps.setCapability("deviceName", "realme RMX1971");
//        caps.setCapability("platformVersion", "11");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("enforceAppInstall", true);
        caps.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-debug (1).apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        assertNotEquals(driver, null);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        assertNotEquals(wait, null);
        Thread.sleep(5000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        stopAppiumServer();
    }
    private void startAppiumServer() {
        try {
            Runtime.getRuntime().exec("appium");
            TimeUnit.SECONDS.sleep(10); // Wait for Appium server to start (adjust as necessary)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopAppiumServer() {
        try {
            Runtime.getRuntime().exec("pkill -9 node");
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void initialLandingPageText() throws InterruptedException{
        Thread.sleep(2000);
        WebElement startCta = driver.findElement(AppiumBy.className("android.widget.TextView"));
        String expectedCtaText = "Get started";
        assertTrue(startCta.getText().equalsIgnoreCase("Get started"));

    }

    @Test
    public void mainLandingPageText() throws InterruptedException{
        TouchAction touchAction = new TouchAction(driver);
        assertNotEquals(touchAction, null);
        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        WebElement enterMobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/label"));
        WebElement defaultMobilePrefix = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='+91']"));
        WebElement mobileNumberInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        WebElement otpCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        WebElement termsAndConditionLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_policy"));
        WebElement termsAndConditionCheckbox = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cb_check"));
        WebElement emailLogin = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='E-mail based log in is no longer available.\nPlease click here to add your mobile number.']"));
        String mobileNumberLabelExpectedText = "Please enter Mobile Number";
        String expectedMobilePrefix = "+91";
        String mobileNUmberPlaceholderText = "Mobile Number";
        String otpCtaText = "Send OTP";
        String termsAndConditionText = "By continuing you agree to Smytten's Privacy Policy & Terms of Service.";
        String emailLoginText = "E-mail based log in is no longer available.\n" +
                "Please click here to add your mobile number.";

        String actualMobileNumberLabelExpectedText = enterMobileNumberLabel.getText();
        assertTrue(actualMobileNumberLabelExpectedText.equalsIgnoreCase(mobileNumberLabelExpectedText));

        String actualExpectedMobilePrefix = defaultMobilePrefix.getText();
        assertTrue(expectedMobilePrefix.equalsIgnoreCase( actualExpectedMobilePrefix.trim()));

        String actualMobileNumberPlaceholderText = mobileNumberInput.getText();
        assertTrue(mobileNUmberPlaceholderText.equalsIgnoreCase(actualMobileNumberPlaceholderText));

        String actualOtpCtaText = otpCta.getText();
        assertTrue(otpCtaText.equalsIgnoreCase(actualOtpCtaText));

        String actualTermsAndConditionText = termsAndConditionLabel.getText();
        assertTrue(termsAndConditionText.equalsIgnoreCase(actualTermsAndConditionText));

        String actualEmailLoginText = emailLogin.getText();
        assertTrue(emailLoginText.trim().equalsIgnoreCase(actualEmailLoginText.trim()));
    }
}
