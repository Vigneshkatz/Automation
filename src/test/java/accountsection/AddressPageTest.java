package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.account.AddressPage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.user.Address;
import org.smytten.user.PinCodeDetails;
import org.smytten.util.Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

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
    public static String MOBILE_NUMBER = null;
    public static Map<Integer, PinCodeDetails> pincode = defaultPincode();
    static List<Address> addressList = new ArrayList<>();
    private static int addressEntryCount = 1;
    private Boolean isEmpty = true;
    private final Boolean isNewUser = true;

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
    public void testLoginWithCorrectOTP() {
        try {
            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile input field not found", mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(LoginPage.getMOBILE_NUMBER(1));

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            WebElement otpContainer = OtpPage.getOtpContainer(driver);
            assertNotNull("OTP container not found", otpContainer);

            WebElement otpEnterInput = OtpPage.getOtpEnterInput(driver);
            otpEnterInput.click();

            //  otpLabel = OtpPage.getOtpLabel(driver);
//            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
//            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
//            otpEnterInput = OtpPage.getOtpEnterInput(driver);
//            assertTrue(OtpPage.OTP_NOT_RECEIVED_LABEL_TEXT.equalsIgnoreCase(otpLabel.getText().trim()));
//            assertTrue(OtpPage.CONTACT_US_EMAIL_LABEL_TEXT.equalsIgnoreCase(mobileNumberLabel.getText().trim()));
//            assertTrue(OtpPage.MOBILE_NUMBER_EDIT_CTA_TEXT.equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
//            otpEnterInput.click();
            // enter otp
            enterOTP(OtpPage.VALID_OTP);
            System.out.println("OTP typed successfully." + OtpPage.VALID_OTP);
        } catch (AssertionError | Exception e) {
            fail("loginWithCrtOTP assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void testCheckPopUp() {
        try {
            if (VerifyElementHelper.isPopupPresent(driver)) {
                WebElement popUpClose = PopUp.getPopUpClose(driver);
                assertNotNull("Popup close button not found", popUpClose);
                popUpClose.click();
                assertTrue("Popup successfully closed", true);
            } else {
                assertTrue("No popUp present", true);
            }
        } catch (AssertionError | Exception e) {
            fail("checkPopUp " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testAccountPageNavigation() {
        try {
            Navigation.getProfileHomeTab(driver).click();
            assertTrue(true);
        } catch (AssertionError | Exception e) {
            Assert.fail("gotoAccountPage assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void gotoAddressPage() {
        try {
            WebElement menu = Navigation.getProfileHomeTab(driver);
            assertNotNull("Menu element is not null", menu);

            WebElement savedAddress = AccountPage.getSavedAddress(driver);
            if (savedAddress != null) {
                savedAddress.click();
                assertTrue("Saved address tapped successfully.", true);
            } else {
                fail("No saved address found");
            }
        } catch (AssertionError | Exception e) {
            fail("gotoAddressPage" + "Saved address element not found." + e.getMessage());
        }
    }

    @Test(priority = 4)
    private void verifyAddressPage() {
        try {
            WebElement addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));

            // Assert common elements
            assertEquals("Shipping Address", addressTitle.getText());
            assertEquals("Add New Address +", addNewAddressCta.getText());

            if (isEmpty) {
                WebElement noAddressWarning = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
                WebElement placeholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));

                assertNotNull("Placeholder image is displayed", placeholderImage);
                assertEquals("Sorry, you have no saved address.", noAddressWarning.getText().toLowerCase());

                Thread.sleep(2000);
                addNewAddressCta.click();
            } else {

                Thread.sleep(1000);
                addNewAddressCta.click();
            }
        } catch (AssertionError | Exception e) {
            fail("verifyAddressPag" + "Address page elements not found. " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void testUpdateAddress() {
        try {
            WebElement firstName = AddressPage.getFirstNameField(driver);
            WebElement lastName = AddressPage.getLastNameField(driver);
            WebElement phoneNumber = AddressPage.getMobileElement(driver);
            WebElement houseNumber = AddressPage.getHouseField(driver);
            WebElement streetName = AddressPage.getStreetField(driver);
            WebElement landmark = AddressPage.getLandmarkField(driver);
            WebElement email = null;
            if (isNewUser) {
                email = AddressPage.getEmailField(driver);
                email.click();
                email.clear();
                email.sendKeys(Utility.generateRandomEmail());
            }
            WebElement pincode = AddressPage.getPincodeField(driver);
            WebElement city = AddressPage.getCityField(driver);
            WebElement state = AddressPage.getStateField(driver);
            WebElement saveAddress = AddressPage.getProceedButton(driver);

            // Clear and set values for fields
            clearAndSetField(firstName, FIRST_NAME);
            clearAndSetField(lastName, LAST_NAME);
            clearAndSetField(phoneNumber, MOBILE_NUMBER == null ? "9500752205 " : MOBILE_NUMBER);
            clearAndSetField(houseNumber, HOUSE_NO);
            clearAndSetField(streetName, STREET);
            clearAndSetField(pincode, PINCODE);
            clearAndSetField(landmark, LANDMARK);

            // Select a random address type
            Random random = new Random();
            int randomIndex = random.nextInt(ADDRESS_TYPE.length);
            String randomAddressType = ADDRESS_TYPE[randomIndex];
            WebElement addressType = AddressPage.getAddressFieldType(randomAddressType, driver);
            addressType.click();

            // Set default address checkbox
            WebElement defaultAddress = AddressPage.getDefaultAddressCheckbox(driver);
            defaultAddress.click();

            // Verify city and state
            assertEquals(city.getText().toLowerCase(), CITY.toLowerCase());
            assertEquals(state.getText().toLowerCase(), STATE.toLowerCase());

            // Click save address button
            saveAddress.click();
            Thread.sleep(2000);
        } catch (AssertionError | Exception e) {
            Assert.fail("updateAddress assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void testChangeDefaultAddress() {
        try {
            WebElement addressList = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_address"));
            List<WebElement> addressCards = addressList.findElements(AppiumBy.id("com.app.smytten.debug:id/cl_root"));

            if (addressCards.isEmpty()) {
                assertTrue("No addresses found", true);
                return;
            }

            if (addressCards.size() == 1) {
                assertTrue("Only one address found", true);
                return;
            }

            WebElement defaultCta = null;
            String defaultAddressName = null;
            for (WebElement addressCard : addressCards) {
                try {
                    defaultCta = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_set_default"));

                    defaultAddressName = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_title")).getText();

                    defaultCta.click();
                    break;
                } catch (NoSuchElementException e) {
                    assertTrue("Only one address found and it is already default", true);
                }
            }

            assertEquals("Default address name matches", defaultAddressName, driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_title")).getText());
        } catch (AssertionError | Exception e) {
            Assert.fail("changeDefaultAddress assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void deleteAddress() {
        try {
            WebElement addressList = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_address"));
            List<WebElement> addressCards = addressList.findElements(AppiumBy.id("com.app.smytten.debug:id/cl_root"));

            // Check if there are any addresses left to delete
            if (addressCards.isEmpty()) {
                assertTrue("No more addresses left to delete", true);
                return;
            }

            int initialAddressSize = addressCards.size();
            int deletedAddressCount = 0;

            while (deletedAddressCount < initialAddressSize) {
                // Get the first address card
                WebElement addressCard = addressCards.get(0);

                // Click on the delete icon
                WebElement deleteIcon = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_delete"));
                deleteIcon.click();

                // Wait for the delete confirmation popup
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement popUp = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.app.smytten.debug:id/cl_root")));

                // Click on the confirm delete button
                WebElement ctaRightElement = popUp.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_cta_right"));
                ctaRightElement.click();
                wait.until(ExpectedConditions.numberOfElementsToBeLessThan(AppiumBy.id("com.app.smytten.debug:id/cl_root"), initialAddressSize - deletedAddressCount));
                deletedAddressCount++;
                addressCards = addressList.findElements(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            }

        } catch (NoSuchElementException e) {
            assertTrue("No more addresses left to delete", true);
        } catch (Exception e) {
            Assert.fail("Failed to delete address: " + e.getMessage());
        }
    }


    //    @Test(priority = 0)
    public void testSignUp() {
        try {
            // Enter mobile number and proceed to OTP
            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile number input field not found", mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(Utility.getNumber());

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            // Choose gender
            WebElement maleElement = SignUpPage.getMaleGenderOption(driver);
            WebElement femaleElement = SignUpPage.getFemaleGenderOption(driver);
            WebElement chooseGender = (Utility.RANDOMNUMBER == 0) ? maleElement : femaleElement;
            chooseGender.click();

            // Select birth month
            SignUpPage.getMonthSpinner(driver).click();
            SignUpPage.getMarchMonthOption(driver).click();

            // Select birth year
            SignUpPage.getYearSpinner(driver).click();
            SignUpPage.getYear2009Option(driver).click();

            // Enter referral code
            WebElement referralInput = SignUpPage.getReferralInput(driver);
            referralInput.click();
            referralInput.sendKeys(SignUpPage.GROUP_INVITE_CODE);

            // Apply referral code
            SignUpPage.getReferralApplyBtn(driver).click();

            // Wait for referral success message
            WebElement referralSuccessTitle = SignUpPage.getReferralSuccessTitle(driver);
            assertNotNull("Referral success title not found", referralSuccessTitle);
            System.out.println(referralSuccessTitle.getText());

            // Confirm referral success payment title
            WebElement referralSuccessPaymentTitle = SignUpPage.getReferralSuccessPaymentTitle(driver);
            System.out.println(referralSuccessPaymentTitle.getText());

            // Confirm signup
            SignUpPage.getConfirmBtn(driver).click();
        } catch (AssertionError | Exception e) {
            fail("signUp" + e.getMessage());
        }
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
        } catch (AssertionError | Exception e) {
            Assert.fail("addMultipleAddress assertion failed: " + e.getMessage());
        }
        addressEntryCount++;
    }

    private void enterOTP(String otp) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("adb shell input text " + otp);
        process.waitFor();
        System.out.println("OTP typed successfully: " + otp);
    }

    private void clearAndSetField(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
        driver.navigate().back();
    }
}
