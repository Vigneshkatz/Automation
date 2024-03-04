package signin;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.clipboard.ClipboardContentType;
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
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class LoginTest {
    public static final String MOBILE_NUMBER = "5111111111";
    public static final String VALID_OTP = "1234";
    public static final String INVALID_OTP = "1111";
    public static final String INVALID_OTP_MESSAGE = "OTP does not match. Please enter the correct OTP.";
    public static final int OTP_MAX_LIMIT = 6;
    public static final String OTP_MAX_LIMIT_MESSAGE = "You have already made maximum number of attempt to verify OTP. Please try again after some time.";
    public static final int PRIVACY_X_COORDINATE =  811;
    public static final int PRIVACY_Y_COORDINATE = 1999;
    public static final int TERMS_X_COORDINATE = 516;
    public static final int TERMS_Y_COORDINATE =  2049;
    public static final int OTP_MAX_SENT_COUNT = 6;
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
    public void loginWithCrtOTP() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(5000);
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys(MOBILE_NUMBER);
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
        Thread.sleep(5000);
        try {
            WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_otp_container"));
            assertNotEquals(otpContainer, null);
            WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_otp_label"));
            WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile"));
            WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile_ed"));
            WebElement otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
            String otpLabelText = "Enter OTP sent via SMS";
            String mobileNumberLabelText = "+91-" + MOBILE_NUMBER;
            String mobileNumberEditCtaText = "Edit";
            String otpNotReceivedLabelText = "Didn’t receive the OTP?";
            String otpResendText = "Resend";
            String contactUsEmailLabelText = "Having trouble signing in? Write to us! -> ";
            assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
            assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
            assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
            otpEnterInput.click();
//            enter otp
            Process process = Runtime.getRuntime().exec("adb shell input text " + VALID_OTP);
            process.waitFor();
            System.out.println("OTP typed successfully."+ VALID_OTP);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
        Thread.sleep(3000);
    }

    @Test
    public void loginWithWrongOTP() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(5000);
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys(MOBILE_NUMBER);
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
        Thread.sleep(5000);
        try {
            WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_otp_container"));
            assertNotEquals(otpContainer, null);
            WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_otp_label"));
            WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile"));
            WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile_ed"));
            WebElement otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
            String otpLabelText = "Enter OTP sent via SMS";
            String mobileNumberLabelText = "+91-" + MOBILE_NUMBER;
            String mobileNumberEditCtaText = "Edit";
            assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
            assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
            assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
            otpEnterInput.click();
//            enter otp
            Process process = Runtime.getRuntime().exec("adb shell input text " + INVALID_OTP);
            process.waitFor();
            WebElement otpToastMessage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/snackbar_text"));
            System.out.println(otpToastMessage.getText());
            assertTrue("InValid Otp Message",otpToastMessage.getText().equals(INVALID_OTP_MESSAGE));
            System.out.println("OTP typed successfully."+ INVALID_OTP);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
        Thread.sleep(3000);
    }

    @Test
    public void copyOtp() throws InterruptedException {
        String decodedOtp = null;
        Thread.sleep(2000);
        driver.openNotifications();
        Thread.sleep(2000);
        WebElement clearNotification = null;
        try {
            clearNotification = driver.findElement(AppiumBy.id("com.android.systemui:id/clear_all_port"));
            clearNotification.click();
        } catch (Exception e) {
            TouchAction touchAction = new TouchAction(driver);
            int xCoordinate = 627;
            int yCoordinate = 1608;
            touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
            System.out.println("no new notification");
            Thread.sleep(2000);
        }
        loginWithCrtOTP();
        try {
            Thread.sleep(20000);
            driver.openNotifications();
            Thread.sleep(2000);
            WebElement actionContainer = driver.findElement(AppiumBy.id("android:id/actions_container_layout"));
            WebElement copyAction = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Copy Verification Code\"]"));
            copyAction.click();
            System.out.println("copied otp");
            String otp = driver.getClipboard(ClipboardContentType.PLAINTEXT);
            System.out.println(otp);
            byte[] decodedBytes = Base64.getDecoder().decode(otp);
            String decodedText = new String(decodedBytes);
            System.out.println(decodedOtp);
            try {
                clearNotification = driver.findElement(AppiumBy.id("com.android.systemui:id/clear_all_port"));
                clearNotification.click();
            } catch (Exception e) {
                TouchAction touchAction = new TouchAction(driver);
                int xCoordinate = 627;
                int yCoordinate = 1608;
                touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
                System.out.println("no new notification");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void otpMaxLimitCheck()throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(5000);
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + MOBILE_NUMBER);
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys(MOBILE_NUMBER);
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
        Thread.sleep(5000);
        try {
            WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_otp_container"));
            assertNotEquals(otpContainer, null);
            WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_otp_label"));
            WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile"));
            WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile_ed"));
            WebElement otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
            String otpLabelText = "Enter OTP sent via SMS";
            String mobileNumberLabelText = "+91-" + MOBILE_NUMBER;
            String mobileNumberEditCtaText = "Edit";
            assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
            assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
            assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
            for (int i = 1; i<=OTP_MAX_LIMIT ; i++) {
                otpEnterInput.click();
                Process process = Runtime.getRuntime().exec("adb shell input text " + INVALID_OTP);
                process.waitFor();
                WebElement otpToastMessage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/snackbar_text"));
                String otpToastText = otpToastMessage.getText();
                System.out.println("OTP typed successfully." + INVALID_OTP);
                System.out.println(otpToastText);
                if(i == (OTP_MAX_LIMIT))
                {
                    assertTrue("InValid Otp Message", otpToastText.equals(OTP_MAX_LIMIT_MESSAGE));
                }else {
                    assertTrue("InValid Otp Message", otpToastText.equals(INVALID_OTP_MESSAGE));
                }
                Thread.sleep(1000);

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
        Thread.sleep(3000);
    }

    @Test
    public void resentOtp() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(5000);
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + MOBILE_NUMBER);
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys(MOBILE_NUMBER);
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
        Thread.sleep(5000);
        try {
            WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_otp_container"));
            assertNotEquals(otpContainer, null);
            WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_otp_label"));
            WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile"));
            WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile_ed"));
            WebElement otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
            String otpLabelText = "Enter OTP sent via SMS";
            String mobileNumberLabelText = "+91-" + MOBILE_NUMBER;
            String mobileNumberEditCtaText = "Edit";
            assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
            assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
            assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
            Thread.sleep(30000);
            WebElement resendOtp = null;
            for (int i = 1; i<=OTP_MAX_SENT_COUNT ; i++) {
                resendOtp = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_otp_timer"));
                resendOtp.click();
                System.out.println("Resend otp click times -> " +i);
                if(i == (OTP_MAX_SENT_COUNT))
                {
                    try {
                        assertNotEquals(null,driver.findElement(AppiumBy.id("com.app.smytten.debug:id/title_template")));
                    }catch (Exception e){
                        fail("toast message not came");
                    }
                }
                Thread.sleep(30000);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
        Thread.sleep(3000);
    }

    @Test
    public void openTermsAndPolicy() throws InterruptedException{
        TouchAction touchAction = new TouchAction(driver);
        assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(5000);
        touchAction.tap(PointOption.point(PRIVACY_X_COORDINATE, PRIVACY_Y_COORDINATE)).perform();
        WebElement privacyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
        Thread.sleep(2000);
        assertEquals("Smytten's Privacy Policy", privacyTitle.getText());
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(544, 211)).perform();
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(TERMS_X_COORDINATE, TERMS_Y_COORDINATE)).perform();
        WebElement termsTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
        Thread.sleep(2000);
        assertEquals("Terms of Service", termsTitle.getText());
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(544, 211)).perform();
        Thread.sleep(2000);
    }
}
