import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest {
    private AndroidDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "OnePlus LE2111");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("enforceAppInstall", true);
        caps.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-prodDebug.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        Assert.assertNotEquals(driver, null);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertNotEquals(wait, null);
        Thread.sleep(2000);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void loginWithCrtOTP() throws InterruptedException {
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
        //        if beta build
        WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/cl_otp_container"));
        Assert.assertNotEquals(otpContainer,null);
        WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_otp_label"));
        WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_mobile"));
        WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_mobile_ed"));
        WebElement otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
        WebElement otpNotReceiveLabel =  driver.findElement(AppiumBy.className("com.app.smytten.beta:id/tv_otp"));
        WebElement otpResendTimer =  driver.findElement(AppiumBy.className("com.app.smytten.beta:id/tv_otp_timer"));
        WebElement contactUsEmail =  driver.findElement(AppiumBy.className("com.app.smytten.beta:id/btn_email"));
        String otpLabelText = "Enter OTP sent via SMS";
        String mobileNumberLabelText = "+91-8610496028";
        String mobileNumberEditCtaText = "Edit";
        String otpNotReceivedLabelText = "Didn’t receive the OTP?";
        String otpResendText = "Resend";
        String contactUsEmailLabelText = "Having trouble signing in? Write to us! -> ";
        assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
        assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
        assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
        assertTrue(otpNotReceivedLabelText.trim().equalsIgnoreCase(otpNotReceiveLabel.getText().trim()));
        assertTrue(contactUsEmailLabelText.trim().equalsIgnoreCase(contactUsEmail.getText().trim()));
        Thread.sleep(60000);
        assertTrue(otpResendText.trim().equalsIgnoreCase(otpResendTimer.getText().trim()));
        for(int i = 0 ; i< 4 ; i++) {
            otpEnterInput.sendKeys("1");
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
    public void loginBeta() throws InterruptedException{
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
        Assert.assertNotEquals(otpContainer,null);
        WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_otp_label"));
        WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_mobile"));
        WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_mobile_ed"));
        WebElement otpEnterInput = driver.findElement(AppiumBy.xpath("(//android.widget.FrameLayout[@resource-id=\"com.app.smytten.beta:id/otp_view\"])[1]/android.widget.LinearLayout"));
//        WebElement otpNotReceiveLabel =  driver.findElement(AppiumBy.xpath("(//android.widget.FrameLayout[@resource-id=\"com.app.smytten.beta:id/otp_view\"])[1]/android.widget.LinearLayout"));
       Thread.sleep(2000);
        WebElement otpResendTimer =  driver.findElement(AppiumBy.id("com.app.smytten.beta:id/tv_otp_timer"));
        WebElement contactUsEmail =  driver.findElement(AppiumBy.id("com.app.smytten.beta:id/btn_email"));
        String otpLabelText = "Enter OTP sent via SMS";
        String mobileNumberLabelText = "+91-"+mobileNumber;
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
        for(int i = 2 ; i<=5 ; i++) {
            String xpath = "(//android.widget.FrameLayout[@resource-id=\"com.app.smytten.beta:id/otp_view\"])[" + (i) + "]/android.widget.TextView";
            driver.findElement(AppiumBy.xpath(xpath)).click();
            System.out.println(xpath);
            if( driver.findElement(AppiumBy.xpath(xpath))!=null) {
                driver.findElement(AppiumBy.xpath("(//android.widget.FrameLayout[@resource-id=\"com.app.smytten.beta:id/otp_view\"])[1]/android.widget.LinearLayout")).sendKeys("1");
            }
        }
        Thread.sleep(3000);

    }

}
