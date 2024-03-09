package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.smytten.user.Address;
import org.smytten.user.PinCodeDetails;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class AddressPageTest extends BaseTest {
    public static final String FIRST_NAME = "VIGNESH";
    public static final String LAST_NAME = "M";
    public static final String HOUSE_NO = "3/182 THIPPANAPALLI VILLAGE AND POST";
    public static final String STREET = "THIPPANAPALLI";
    public static final String LANDMARK = "KUNDRAPALLI X ROAD";
    public static final String PINCODE = "635115";
    public static final String STATE = "TAMIL NADU";
    public static final String CITY = "RAMAPURAM";
    public static final String[] ADDRESS_TYPE = {"Office", "Home", "Other"};
    private static final int RANDOMNUMBER = ThreadLocalRandom.current().nextInt(0, 2);
    public static String MOBILE_NUMBER = null;
    public static HashMap<Integer, HashMap<String, String>> address = new HashMap<>();
    public static Map<Integer, PinCodeDetails> pincode = defaultPincode();
    static List<Address> addressList = new ArrayList<>();
    private static int addressEntryCount = 1;
    private static TouchAction touchAction;
    private WebDriverWait wait;
    private Boolean isEmpty = true;
    private Boolean isNewUser = true;

    public static void addAddress(int noOfAddress) {

        for (int i = 1; i <= noOfAddress; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(5) + 1;
            Address address = new Address(Utility.generateRandomString(10), Utility.generateRandomString(5), Utility.generateRandomEmail(), Utility.getNumber(), Utility.generateRandomString(8), Utility.generateRandomString(20), Utility.generateRandomString(20), pincode.get(randomNumber).pinCode, pincode.get(randomNumber).city, pincode.get(randomNumber).state);
            addressList.add(address);
        }
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


    @Test(priority = 0)
    public void login() throws InterruptedException {
        try {
            WebElement startCta = driver.findElement(AppiumBy.className("android.widget.TextView"));
            startCta.click();
            WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
            assertNotEquals(mobileInput, null);
            System.out.println("Mobile input text: " + mobileInput.getText());
            mobileInput.click();
            mobileInput.click();
            mobileInput.click();
            mobileInput.sendKeys("1058032280");

            WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
            assertNotEquals(proceedBtn, null);
            System.out.println("Proceed button text: " + proceedBtn.getText());
            proceedBtn.click();
            this.isEmpty = false;
            this.isNewUser = false;
        } catch (Exception e) {
            e.printStackTrace();
            fail("login attempt fail");
        }

//        Thread.sleep(5000);
//        try {
//            WebElement otpContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_otp_container"));
//            assertNotEquals(otpContainer, null);
//            WebElement otpLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_otp_label"));
//            WebElement mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile"));
//            WebElement mobileNumberEditCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_mobile_ed"));
//            WebElement otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
//            String otpLabelText = "Enter OTP sent via SMS";
//            String mobileNumberLabelText = "+91-" + MOBILE_NUMBER;
//            String mobileNumberEditCtaText = "Edit";
//            assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
//            assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
//            assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
//            otpEnterInput.click();
////            enter otp
//            Process process = Runtime.getRuntime().exec("adb shell input text " + 1111);
//            process.waitFor();
//            System.out.println("OTP typed successfully."+ 1111);
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            fail();
//        }
//        Thread.sleep(3000);
    }

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void gotoAccountPage() {
        try {
            WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
            mobileInput.click();
            assertTrue(true);
        } catch (Exception e) {
            fail("failed to go account page");
        }

    }

    @Test(priority = 3)
    public void gotoAddressPage() {
        try {
            WebElement menu = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_menu"));
            WebElement savedAddress = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"My Saved Address\"));"));
            if (savedAddress != null) {
                savedAddress.click();
                assertTrue("Saved address tapped successfully.", true);
            } else {
                fail("No address found");
                System.out.println("Saved address not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("failed to go to address page");
        }

    }

    @Test(priority = 4)
    private void verifyAddressPage() throws InterruptedException {
        if (isEmpty) {
            try {
                WebElement addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
                WebElement noAddressWarning = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
                WebElement addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));
                WebElement placeholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));
                assertNotNull(placeholderImage);

                assertEquals("Shipping Address".toLowerCase(), addressTitle.getText().toLowerCase());
                assertEquals("Add New Address +".toLowerCase(), addNewAddressCta.getText().toLowerCase());
                assertEquals("Sorry, you have no saved address.".toLowerCase(), noAddressWarning.getText().toLowerCase());
                Thread.sleep(2000);
                addNewAddressCta.click();
            } catch (Exception e) {
                e.printStackTrace();
                fail("something missing in empty address page" + e.getMessage());
            }
        } else {
            WebElement addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));
            Thread.sleep(1000);
            assertEquals("Shipping Address".toLowerCase(), addressTitle.getText().toLowerCase());
            assertEquals("Add New Address +".toLowerCase(), addNewAddressCta.getText().toLowerCase());
            Thread.sleep(2000);
            addNewAddressCta.click();
        }
    }

    @Test(priority = 5)
    public void updateAddress() {
        try {
            WebElement firstName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_fname"));
            WebElement lastName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_lname"));
            WebElement phoneNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
            WebElement houseNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_house"));
            WebElement streetName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_street"));
            WebElement landmark = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_landmark"));
            WebElement email = null;
            if (isNewUser) {
                email = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_email"));
            }
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
            phoneNumber.sendKeys(MOBILE_NUMBER == null ? "9500752205 " : MOBILE_NUMBER);
            driver.navigate().back();
            if (isNewUser) {
                email.click();
                email.clear();
                email.sendKeys(Utility.generateRandomEmail());
            }
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

    @Test(priority = 6)
    public void changeDefaultAddress() throws InterruptedException {
        String defaultAddressName = null;
        WebElement addressList = null;
        List<WebElement> addressCards = null;
        try {
            addressList = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_address"));
            addressCards = addressList.findElements(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            if (addressCards != null && addressCards.isEmpty()) {
                assertTrue("no address is there", true);
            }

            if (addressCards.size() == 1) {
                assertTrue("onlyone address is there", true);
            }
            for (WebElement addressCard : addressCards) {
                WebElement defaultCta = null;
                try {
                    defaultCta = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_set_default"));
                    if (defaultCta == null) {
                        assertTrue("only one card is there and it is default", true);
                        return;
                    }
                    defaultAddressName = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_title")).getText();
                    defaultCta.click();
                    break;
                } catch (NoSuchElementException e) {
                    assertTrue("only one card is there and it is default", true);
                    e.printStackTrace();
                }
            }
            assertEquals(defaultAddressName, driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_title")).getText());

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("Failed to change Default Address: Element not found");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to change Default Address: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void deleteAddress() {
        try {
            int initialAddressSize;
            WebElement addressList = null;
            List<WebElement> addressCards;

            while (true) {
                try {
                    addressList = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_address"));
                    addressCards = addressList.findElements(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
                    initialAddressSize = addressCards.size();

                    if (initialAddressSize == 0) {
                        break;
                    }
                    WebElement addressCard = addressCards.get(0); // Get the first address card
                    WebElement deleteIcon = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_delete"));
                    deleteIcon.click();

                    try {
                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                        WebElement popUp = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.app.smytten.debug:id/cl_root")));

                        WebElement ctaRightElement = popUp.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_cta_right"));
                        ctaRightElement.click();

                        wait.until(ExpectedConditions.numberOfElementsToBeLessThan(AppiumBy.id("com.app.smytten.debug:id/cl_root"), initialAddressSize));
                    } catch (StaleElementReferenceException e) {
                        continue;

                    }
                } catch (Exception e) {
                    assertTrue("not more address left to delete", true);
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            fail("Elements not found");
        }
    }

    //    @Test(priority = 0)
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
        MOBILE_NUMBER = Utility.getNumber();
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

    public void addMultipleAddress() throws InterruptedException {
        this.isEmpty = false;
        verifyAddressPage();
        if (addressEntryCount == 1) {
            addAddress(5);
        }
        try {
            WebElement firstName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_fname"));
            WebElement lastName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_lname"));
            WebElement phoneNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
            WebElement houseNumber = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_house"));
            WebElement streetName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_street"));
            WebElement landmark = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_landmark"));
            WebElement pincode = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_pincode"));
            WebElement city = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_city"));
            WebElement state = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_state"));
            WebElement saveAddress = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
            firstName.click();
            firstName.clear();
            firstName.sendKeys(addressList.get(addressEntryCount - 1).firstName);
            driver.navigate().back();
            lastName.click();
            lastName.clear();
            lastName.sendKeys(addressList.get(addressEntryCount - 1).lastName);
            driver.navigate().back();
            phoneNumber.click();
            phoneNumber.clear();
            phoneNumber.sendKeys(addressList.get(addressEntryCount - 1).phone);
            driver.navigate().back();
            houseNumber.click();
            houseNumber.sendKeys(addressList.get(addressEntryCount - 1).houseNumber);
            driver.navigate().back();
            streetName.click();
            streetName.sendKeys(addressList.get(addressEntryCount - 1).streetName);
            driver.navigate().back();
            pincode.click();
            pincode.clear();
            pincode.sendKeys(addressList.get(addressEntryCount - 1).pinCode);
            driver.navigate().back();
            landmark.click();
            landmark.sendKeys(addressList.get(addressEntryCount - 1).landmark);
            driver.navigate().back();
            Random random = new Random();
            int randomIndex = random.nextInt(ADDRESS_TYPE.length);
            String randomAddressType = ADDRESS_TYPE[randomIndex];
            WebElement addressType = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + randomAddressType + "\"));"));
            addressType.click();
            if (randomIndex == 1) {
                WebElement defaultAddress = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Make this my default address\"));"));
                defaultAddress.click();
            }
            assertEquals(city.getText().toLowerCase(), addressList.get(addressEntryCount - 1).city.toLowerCase());
            assertEquals(state.getText().toLowerCase(), addressList.get(addressEntryCount - 1).state.toLowerCase());
            saveAddress.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            fail("update address failed");
        }
        addressEntryCount++;
    }

}
