import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class AddressPageTest {
    public static final String MOBILE_NUMBER = "5111111111";
    public static final String FIRST_NAME = "VIGNESH";
    public static final String LAST_NAME = "M";
    public static final String HOUSE_NO = "3/182 THIPPANAPALLI VILLAGE AND POST";
    public static final String STREET = "THIPPANAPALLI";
    public static final String LANDMARK = "KUNDRAPALLI X ROAD";
    public static final String PINCODE = "635115";
    public static final String STATE = "TAMIL NADU";
    public static final String CITY = "RAMAPURAM";
    private static final int RANDOMNUMBER = ThreadLocalRandom.current().nextInt(0, 2);
    private static final long MIN_NUMBER = 1000000000L;
    private static final long MAX_NUMBER = 5999999999L;
    public static HashMap<Integer, HashMap<String, String>> address = new HashMap<>();
    private static long last = MIN_NUMBER - 1;
    private static TouchAction touchAction;
    private static AndroidDriver driver;
    private WebDriverWait wait;

    private static long getNumber() {
        Random random = new Random();
        long firstDigit = random.nextInt(5) + 1; // Generates a random number between 1 and 5
        long id = (firstDigit * 1000000000L) + (random.nextLong() % 1000000000L);
        if (id <= last) {
            id = last % (MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        }
        return last = id;
    }

    @BeforeSuite
    public static void setUp() throws MalformedURLException, InterruptedException {
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
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        stopAppiumServer();
    }

    private static void startAppiumServer() {
        try {
            Runtime.getRuntime().exec("appium");
            TimeUnit.SECONDS.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void stopAppiumServer() {
        try {
            Runtime.getRuntime().exec("pkill -9 node");
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString();
        return "katziio" + uuid.substring(0, 8) + "@smytten.com";
    }

    public static void addAddress(int noOfAddress) {
        Map<Integer, PinCodeDetails> pinCodeMap = defaultPincode();
        for (int i = 1; i <= noOfAddress; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(5) + 1;
            HashMap<String, String> addressDetails = new HashMap<>();
            addressDetails.put("houseNumber", generateRandomString());
            addressDetails.put("streetName", generateRandomString());
            addressDetails.put("pincode", pinCodeMap.get(randomNumber).pinCode);
            addressDetails.put("city", pinCodeMap.get(randomNumber).city);
            addressDetails.put("state", pinCodeMap.get(randomNumber).state);
            address.put(i, addressDetails);
        }
    }

    public static String generateRandomString() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 10;
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static Map<Integer, PinCodeDetails> defaultPincode() {
        Map<Integer, PinCodeDetails> pinCodeMap = new HashMap<>();
        pinCodeMap.put(1, new PinCodeDetails("110001", "New Delhi", "Delhi"));
        pinCodeMap.put(2, new PinCodeDetails("400001", "Mumbai", "Maharashtra"));
        pinCodeMap.put(3, new PinCodeDetails("600001", "Chennai", "Tamil Nadu"));
        pinCodeMap.put(4, new PinCodeDetails("700001", "Kolkata", "West Bengal"));
        pinCodeMap.put(5, new PinCodeDetails("500001", "Hyderabad", "Telangana"));
        return pinCodeMap;
    }

    private void checkPopUp() throws InterruptedException {
        WebElement popUpClose = null;
        try {
            popUpClose = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            popUpClose.click();
        } catch (Exception e) {
            System.out.println("no popUp");
        }
    }

    @Test
    public void openAccountPage() throws InterruptedException {
        signUp();
        checkPopUp();
        gotoAccountPage();
        gotoAddressPage();
        verifyAddressPage();
        updateAddress();
    }

    private void updateAddress() {
        try {
            WebElement firstName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_fname"));
            WebElement lastName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_lname"));
            WebElement phoneNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
            WebElement houseNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_house"));
            WebElement streetName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_street"));
            WebElement landmark = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_landmark"));
            WebElement email = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_email"));
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
            phoneNumber.sendKeys(MOBILE_NUMBER);
            driver.navigate().back();
            email.click();
            email.clear();
            email.sendKeys(generateRandomEmail());
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
            WebElement addressType = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Office\"));"));
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

    private void verifyAddressPage() {
        try {
            WebElement addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement noAddressWarning = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
            WebElement addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));
            WebElement placeholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));
            assertNotNull(placeholderImage);
            assertEquals("Shipping Address".toLowerCase(), addressTitle.getText().toLowerCase(), "page title");
            assertEquals("Add New Address +".toLowerCase(), addNewAddressCta.getText().toLowerCase(), "page title");
            assertEquals("Sorry, you have no saved address.".toLowerCase(), noAddressWarning.getText().toLowerCase(), "page title");
            addNewAddressCta.click();
        } catch (Exception e) {
            fail("something missing in empty address page" + e.getMessage());
        }
    }


    public void gotoAddressPage() throws InterruptedException {
        WebElement menu = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_menu"));
        WebElement savedAddress = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"My Saved Address\"));"));
        if (savedAddress != null) {
            savedAddress.click();
            System.out.println("Saved address tapped successfully.");
        } else {
            System.out.println("Saved address not found.");
        }
    }

    public void gotoAccountPage() throws InterruptedException {
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
        mobileInput.click();
    }

    @Test(invocationCount = 10)
    public void addMultipleAddress() throws InterruptedException {
    }

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
        mobileInput.sendKeys(String.valueOf(getNumber()));
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

    private static class PinCodeDetails {
        String pinCode;
        String city;
        String state;

        PinCodeDetails(String pinCode, String city, String state) {
            this.pinCode = pinCode;
            this.city = city;
            this.state = state;
        }
    }

}
