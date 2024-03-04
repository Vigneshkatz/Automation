package luxe;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class LuxeMemberShipTest {

    public static final String[] ADDRESS_TYPE = {"Office", "Home", "Other"};
    public static String MOBILE_NUMBER = "5111111111";
    public static final String VALID_OTP = "1234";

    private static final int RANDOMNUMBER = ThreadLocalRandom.current().nextInt(0, 2);

    public static final String FIRST_NAME = "VIGNESH";
    public static final String LAST_NAME = "M";
    public static final String HOUSE_NO = "3/182 THIPPANAPALLI VILLAGE AND POST";
    public static final String STREET = "THIPPANAPALLI";
    public static final String LANDMARK = "KUNDRAPALLI X ROAD";
    public static final String PINCODE = "635115";
    public static final String STATE = "TAMIL NADU";
    public static final String CITY = "RAMAPURAM";

    public static final String LUXE_TITLE = "Smytten Luxe";

    private static final long MIN_NUMBER = 1000000000L;
    private static final long MAX_NUMBER = 5999999999L;

    private static long last = MIN_NUMBER - 1;
    private static AndroidDriver driver;
    private WebDriverWait wait;
    private static TouchAction touchAction;
    @BeforeSuite
    public static void setUp() throws MalformedURLException {
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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        touchAction = new TouchAction(driver);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        stopAppiumServer();
    }
    private static void startAppiumServer() {
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
        signUp();
        checkPopUp();
        gotoTrialFront();
        WebElement luxeEntryPointTrial = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_luxe_top"));
        luxeEntryPointTrial.click();
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
    public void checkPopUp() {
        WebElement popUpClose = null;
        try {
            popUpClose = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            popUpClose.click();
            assertTrue("popup successfully closed", true);
        } catch (Exception e) {
            System.out.println("no popUp");
            assertTrue("popup is not there", true);
        }
    }

    private void gotoTrialFront() throws InterruptedException {
        WebElement popUpClose = null;
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
        Thread.sleep(5000);

        touchAction.tap(PointOption.point(625, 827)).perform();
        Thread.sleep(2000);
        WebElement idbiBank = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"bank-item-IBKL\"]/android.view.View"));
        idbiBank.click();
        Thread.sleep(1000);
        touchAction.tap(PointOption.point(544, 2297)).perform();
        Thread.sleep(10000);
        WebElement successPage = driver.findElement(AppiumBy.xpath("//android.webkit.WebView[@text=\"Razorpay Bank\"]"));
        assertNotEquals(null,successPage);
        WebElement successCta = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Success\"]"));
        successCta.click();
        Thread.sleep(5000);

        WebElement orderConfirmScreen = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
        assertNotEquals(null,orderConfirmScreen);

    }

    public void updateAddress() {
        try {
            WebElement firstName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_fname"));
            WebElement lastName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_lname"));
            WebElement phoneNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
            WebElement houseNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_house"));
            WebElement streetName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_street"));
            WebElement landmark = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_landmark"));
            WebElement email = null;
//            if(isNewUser) {
                email = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_email"));
//            }
            WebElement pincode = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_pincode"));
            WebElement city = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_city"));
            WebElement state = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_state"));
            WebElement saveAddress = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
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
            phoneNumber.sendKeys(MOBILE_NUMBER == null ? "9500752205 ": MOBILE_NUMBER);
            driver.navigate().back();
//            if(isNewUser) {
                email.click();
                email.clear();
                email.sendKeys(generateRandomEmail());
//            }
            houseNumber.click();
            houseNumber.sendKeys(HOUSE_NO);
            driver.navigate().back();
            streetName.click();
            streetName.sendKeys(STREET);
            driver.navigate().back();
            pincode.click();
            pincode.clear();
            pincode.sendKeys(PINCODE);
            driver.navigate().back();
            landmark.click();
            landmark.sendKeys(LANDMARK);
            driver.navigate().back();
            Random random = new Random();
            int randomIndex = random.nextInt(ADDRESS_TYPE.length);
            String randomAddressType = ADDRESS_TYPE[randomIndex];
            WebElement addressType = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + randomAddressType + "\"));"));
            addressType.click();
            WebElement defaultAddress = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Make this my default address\"));"));
            defaultAddress.click();
            assertEquals(city.getText().toLowerCase(), CITY.toLowerCase());
            assertEquals(state.getText().toLowerCase(), STATE.toLowerCase());
            saveAddress.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            fail("update address failed");
        }
    }

//    @Test
    public void signUp() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        Thread.sleep(1000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(1000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        MOBILE_NUMBER = String.valueOf(getNumber());
        mobileInput.sendKeys(MOBILE_NUMBER);
        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();

        String maleElementId = "com.app.smytten.debug:id/tv_male";
        String femaleElementId = "com.app.smytten.debug:id/tv_female";
        String selectedGenderId = (RANDOMNUMBER == 0) ? maleElementId : femaleElementId;
        WebElement chooseGender = driver.findElement(AppiumBy.id(selectedGenderId));
        chooseGender.click();

        WebElement chooseMonth = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/month_spinner"));
        chooseMonth.click();
        WebElement selectMonth = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='March']"));
        selectMonth.click();

        WebElement chooseYear = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/year_spinner"));
        chooseYear.click();
        WebElement selectYear = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='2009']"));
        selectYear.click();

        WebElement confirmBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/signup_manual"));
        confirmBtn.click();
    }

    private static long getNumber() {
        Random random = new Random();
        long firstDigit = random.nextInt(5) + 1;
        long id = (firstDigit * 1000000000L) + (random.nextLong() % 1000000000L);
        if (id <= last) {
            id = last % (MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        }
        return last = id;
    }

    public static String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString();
        return "katziio" + uuid.substring(0, 8) + "@smytten.com";
    }


}
