import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
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
import java.util.concurrent.TimeUnit;

import static org.junit.*;

public class LuxeMemberShipTest {
    public static final String MOBILE_NUMBER = "5111111111";
    public static final String VALID_OTP = "1234";

    public static final String FIRST_NAME = "VIGNESH";
    public static final String LAST_NAME = "M";
    public static final String HOUSE_NO = "3/182 THIPPANAPALLI VILLAGE AND POST";
    public static final String STREET = "THIPPANAPALLI";
    public static final String LANDMARK = "KUNDRAPALLI X ROAD";
    public static final String PINCODE = "635115";
    public static final String STATE = "TAMIL NADU";
    public static final String CITY = "RAMAPURAM";

    public static final String LUXE_TITLE = "Smytten Luxe";
    private AndroidDriver driver;
    private WebDriverWait wait;
    private static TouchAction touchAction;
    @Before
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
        touchAction = new TouchAction(driver);
    }

    @After
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
    public void openLuxeMembershipPage() throws InterruptedException {
        login();
        Thread.sleep(5000);
        gotoTrialFront();
        WebElement luxeEntryPointTrial = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_luxe_top"));
        luxeEntryPointTrial.click();
        Thread.sleep(2000);
        WebElement luxeTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_subtitle"));
        String luxeTitleText = luxeTitle.getText();
        assertEquals("Luxe title", LUXE_TITLE, luxeTitleText.trim());
        WebElement luxeProceed = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
        luxeProceed.click();
        Thread.sleep(2000);
        updateAddress();
        completePayment();
        verifyOrderConfirmScreen();
    }

    private void gotoTrialFront() throws InterruptedException {
        WebElement popUpClose = null;
        try{
            popUpClose= driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            popUpClose.click();
        }catch (Exception e) {
            System.out.println("no popUp");
        }
        Thread.sleep(2000);
        try {
            WebElement trialFront = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/trail_home_tab"));
            assertNotNull(trialFront);
            trialFront.click();
            try{
                popUpClose= driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
                popUpClose.click();
            }catch (Exception e) {
                System.out.println("no popUp");
            }
            Thread.sleep(2000);
        }catch (Exception e)
        {
            fail("trial front not found");
        }
    }

    private void verifyOrderConfirmScreen() throws InterruptedException {
        WebElement fullPage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
        WebElement header = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_header"));
        WebElement title = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_size"));
        WebElement orderId = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_order_id"));
        WebElement edd = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_edd"));
        WebElement membershipValidity = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_total_payable"));
        WebElement totalPaid = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_total_payable_rs"));
        WebElement luxeBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_membership_banner"));
        WebElement helpSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_tnc"));
        WebElement exploreCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_explore"));
        assertNotNull("Full page is not null", fullPage);
        assertNotNull("Header is not null", header);
        assertNotNull("Title is not null", title);
        assertNotNull("Order ID is not null", orderId);
        assertNotNull("Estimated Delivery Date is not null", edd);
        assertNotNull("Membership Validity is not null", membershipValidity);
        assertNotNull("Total Paid amount is not null", totalPaid);
        assertNotNull("Luxe Banner is not null", luxeBanner);
        assertNotNull("Help Section is not null", helpSection);
        assertNotNull("Explore CTA is not null", exploreCta);
        assertEquals("Luxe Welcome kit (1 item)",title.getText());
        assertEquals("Luxe membership (1 year)",membershipValidity.getText());
        System.out.println(exploreCta.getText());
        exploreCta.click();
        Thread.sleep(10000);

    }

    private void completePayment() throws InterruptedException {
        WebElement paymentTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_payment_mode"));
        assertEquals("payment title","Select Payment Methods",paymentTitle.getText());
        WebElement upi = driver.findElement(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.app.smytten.debug:id/rv_payment_mode\"]/android.widget.LinearLayout[1]"));
        upi.click();
        WebElement proceedPayment = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
        proceedPayment.click();
        Thread.sleep(2000);

        touchAction.tap(PointOption.point(625, 827)).perform();
        Thread.sleep(5000);
        WebElement idbiBank = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"bank-item-IBKL\"]/android.view.View"));
        idbiBank.click();
        Thread.sleep(1000);
        WebElement bankName = driver.findElement(AppiumBy.id("bank-select"));
        assertEquals("Bank Name",bankName.getText(),"IDBI");
        WebElement proceedCta = driver.findElement(AppiumBy.id("redesign-v15-cta"));
        proceedCta.click();
        Thread.sleep(10000);
        WebElement successPage = driver.findElement(AppiumBy.xpath("//android.webkit.WebView[@text=\"Razorpay Bank\"]"));
        assertNotEquals(null,successPage);
        WebElement successCta = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Success\"]"));
        successCta.click();
        Thread.sleep(5000);

        WebElement orderConfirmScreen = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
        assertNotEquals(null,orderConfirmScreen);

    }

    private void updateAddress() {
        try {
            WebElement firstName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_fname"));
            WebElement lastName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_lname"));
            WebElement phoneNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
            WebElement houseNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/til_house"));
            WebElement streetName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_street"));
            WebElement landmark = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_landmark"));
            WebElement pincode = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_pincode"));
            WebElement city = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_city"));
            WebElement state = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_state"));
            WebElement saveAs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_office"));
            WebElement saveAddress = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
            pincode.click();
            pincode.clear();
            pincode.sendKeys(PINCODE);
            saveAddress.click();
            assertEquals(city.getText().toLowerCase(),CITY.toLowerCase());
            assertEquals(state.getText().toLowerCase(),STATE.toLowerCase());
            saveAs.click();
            firstName.click();
            firstName.clear();
            firstName.sendKeys(FIRST_NAME);
            driver.navigate().back();
            lastName.click();
            lastName.clear();
            lastName.sendKeys(LAST_NAME);
            driver.navigate().back();
            phoneNumber.click();
            phoneNumber.clear();
            phoneNumber.sendKeys(MOBILE_NUMBER);
            driver.navigate().back();
            houseNumber.click();
            houseNumber.sendKeys(HOUSE_NO);
            driver.navigate().back();
            streetName.click();
            streetName.sendKeys(STREET);
            saveAddress.click();
            landmark.click();
            landmark.sendKeys(LANDMARK);
            saveAddress.click();
            Thread.sleep(3000);

        }catch (Exception e)
        {
            e.printStackTrace();
            fail("update address failed");
        }
    }

    @Test
    public void login() throws InterruptedException {
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
            Process process = Runtime.getRuntime().exec("adb shell input text " + VALID_OTP);
            process.waitFor();
            System.out.println("OTP typed successfully."+ VALID_OTP);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
        Thread.sleep(3000);
    }


}
