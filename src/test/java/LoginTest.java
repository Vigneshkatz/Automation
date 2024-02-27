import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    public static final String MOBILE_NUMBER = "5111111111";
    public static final String OTP = "1234";
    private AndroidDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() throws MalformedURLException, InterruptedException {
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
        Assert.assertNotEquals(driver, null);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertNotEquals(wait, null);
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void loginWithCrtOTP() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        Assert.assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(5000);
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        Assert.assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys(MOBILE_NUMBER);
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        Assert.assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
        Thread.sleep(5000);
        try {
            WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_otp_container"));
            Assert.assertNotEquals(otpContainer, null);
            WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_otp_label"));
            WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile"));
            WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile_ed"));
            WebElement otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
//            WebElement otpNotReceiveLabel = driver.findElement(AppiumBy.className("com.app.smytten.debug:id/tv_otp"));
//            WebElement otpResendTimer = driver.findElement(AppiumBy.className("com.app.smytten.debug:id/tv_otp_timer"));
//            WebElement contactUsEmail = driver.findElement(AppiumBy.className("com.app.smytten.debug:id/btn_email"));
            String otpLabelText = "Enter OTP sent via SMS";
            String mobileNumberLabelText = "+91-" + MOBILE_NUMBER;
            String mobileNumberEditCtaText = "Edit";
            String otpNotReceivedLabelText = "Didn’t receive the OTP?";
            String otpResendText = "Resend";
            String contactUsEmailLabelText = "Having trouble signing in? Write to us! -> ";
            assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
            assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
            assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
//            assertTrue(otpNotReceivedLabelText.trim().equalsIgnoreCase(otpNotReceiveLabel.getText().trim()));
//            assertTrue(contactUsEmailLabelText.trim().equalsIgnoreCase(contactUsEmail.getText().trim()));
//            Thread.sleep(60000);
//            assertTrue(otpResendText.trim().equalsIgnoreCase(otpResendTimer.getText().trim()));\
//            String otpXPath = "//android.widget.FrameLayout[@resource-id='com.app.smytten.debug:id/otp_view']";
            otpEnterInput.click();

            Process process = Runtime.getRuntime().exec("adb shell input text " + OTP);
            process.waitFor(); // Wait for the process to finish
            System.out.println("OTP typed successfully.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Thread.sleep(3000);
    }

    @Test
    public void loginWithWrongOTP() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        Assert.assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        Assert.assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys("8610496028");
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        Assert.assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
    }

    @Test
    public void loginBeta() throws InterruptedException {
        String mobileNumber = "9345690376";
        TouchAction touchAction = new TouchAction(driver);
        Assert.assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(2000);
        Thread.sleep(5000);
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/et_mobile"));
        Assert.assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys(mobileNumber);
        Thread.sleep(1000);


        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/proceed"));
        Assert.assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
        Thread.sleep(5000);
        //        if beta build
        WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/cl_otp_container"));
        Assert.assertNotEquals(otpContainer, null);
        WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_otp_label"));
        WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_mobile"));
        WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_mobile_ed"));
        WebElement otpEnterInput = driver.findElement(AppiumBy.xpath("(//android.widget.FrameLayout[@resource-id=\"com.app.smytten.beta:id/otp_view\"])[1]/android.widget.LinearLayout"));
//        WebElement otpNotReceiveLabel =  driver.findElement(AppiumBy.xpath("(//android.widget.FrameLayout[@resource-id=\"com.app.smytten.beta:id/otp_view\"])[1]/android.widget.LinearLayout"));
        Thread.sleep(2000);
        WebElement otpResendTimer = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_otp_timer"));
        WebElement contactUsEmail = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/btn_email"));
        String otpLabelText = "Enter OTP sent via SMS";
        String mobileNumberLabelText = "+91-" + mobileNumber;
        String mobileNumberEditCtaText = "Edit";
//        String otpNotReceivedLabelText = "Didn’t receive the OTP?";
        String otpResendText = "Resend";
        String contactUsEmailLabelText = "Having trouble signing in? Write to us! -> ";
        assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
        assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
        assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
//        assertTrue(otpNotReceivedLabelText.trim().equalsIgnoreCase(otpNotReceiveLabel.getText().trim()));
        assertTrue(contactUsEmailLabelText.trim().equalsIgnoreCase(contactUsEmail.getText().trim()));
//        Thread.sleep(60000);
//        assertTrue(otpResendText.trim().equalsIgnoreCase(otpResendTimer.getText().trim()));
        for (int i = 2; i <= 5; i++) {
            String xpath = "(//android.widget.FrameLayout[@resource-id=\"com.app.smytten.beta:id/otp_view\"])[" + (i) + "]/android.widget.TextView";
            driver.findElement(AppiumBy.xpath(xpath)).click();
            System.out.println(xpath);
            if (driver.findElement(AppiumBy.xpath(xpath)) != null) {
                driver.findElement(AppiumBy.xpath("(//android.widget.FrameLayout[@resource-id=\"com.app.smytten.beta:id/otp_view\"])[1]/android.widget.LinearLayout")).sendKeys("1");
            }
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
        }
    }

}
