package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.account.AddressPage;
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
    private static TouchAction touchAction;
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
    public void login() throws InterruptedException {
        WebElement mobileInput = null;
        WebElement proceedBtn = null;
        WebElement otpContainer = null;
        WebElement otpLabel = null;
        WebElement mobileNumberLabel = null;
        WebElement mobileNumberEditCta = null;
        WebElement otpEnterInput = null;

        try {
            mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull(mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(LoginPage.getMOBILE_NUMBER(1));
            proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();
            otpContainer = OtpPage.getOtpContainer(driver);
            assertNotNull(otpContainer);
            //  otpLabel = OtpPage.getOtpLabel(driver);
//            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
//            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            otpEnterInput = OtpPage.getOtpEnterInput(driver);
//            assertTrue(OtpPage.OTP_NOT_RECEIVED_LABEL_TEXT.equalsIgnoreCase(otpLabel.getText().trim()));
//            assertTrue(OtpPage.CONTACT_US_EMAIL_LABEL_TEXT.equalsIgnoreCase(mobileNumberLabel.getText().trim()));
//            assertTrue(OtpPage.MOBILE_NUMBER_EDIT_CTA_TEXT.equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
            otpEnterInput.click();
            // enter otp
            Process process = Runtime.getRuntime().exec("adb shell input text " + OtpPage.VALID_OTP);
            process.waitFor();
            System.out.println("OTP typed successfully." + OtpPage.VALID_OTP);
            recordResult("loginWithCrtOTP", "Pass");

        } catch (AssertionError e) {
            recordResult("loginWithCrtOTP", "Fail " + e.getMessage());
            Assert.fail("loginWithCrtOTP assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signUp", "Fail " + e.getMessage());
            Assert.fail("loginWithCrtOTP failed: " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void checkPopUp() {
        WebElement popUpClose = null;
        if(VerifyElementHelper.isPopupPresent(driver)){
            popUpClose = PopUp.getPopUpClose(driver);
            popUpClose.click();
            assertTrue("popup successfully closed", true);
            recordResult("checkPopUp", "Pass");

        }
        recordResult("CheckPopUp", "Pass");
    }

    @Test(priority = 2)
    public void gotoAccountPage() {
        try {
            WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
            mobileInput.click();
            assertTrue(true);
            recordResult("gotoAccountPage", "Pass");

        } catch (AssertionError e) {
            recordResult("gotoAccountPage", "Fail " + e.getMessage());
            Assert.fail("gotoAccountPage assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("gotoAccountPage", "Fail " + e.getMessage());
            Assert.fail("gotoAccountPage failed: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void gotoAddressPage() {
        try {
            WebElement menu = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_menu"));
            WebElement savedAddress = AccountPage.getSavedAddress(driver);
            if (savedAddress != null) {
                savedAddress.click();
                assertTrue("Saved address tapped successfully.", true);
            } else {
                fail("No address found");
                System.out.println("Saved address not found.");
            }

            recordResult("gotoAddressPage", "Pass");

        } catch (AssertionError e) {
            recordResult("gotoAddressPage", "Fail " + e.getMessage());
            Assert.fail("gotoAddressPage assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("gotoAddressPage", "Fail " + e.getMessage());
            Assert.fail("gotoAddressPage failed: " + e.getMessage());
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

                recordResult("verifyAddressPage", "Pass");

            } catch (AssertionError e) {
                recordResult("verifyAddressPage", "Fail " + e.getMessage());
                Assert.fail("verifyAddressPage assertion failed: " + e.getMessage());
            } catch (Exception e) {
                recordResult("verifyAddressPage", "Fail " + e.getMessage());
                Assert.fail("verifyAddressPage failed: " + e.getMessage());
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
            WebElement firstName = AddressPage.getFirstNameField(driver);
            WebElement lastName = AddressPage.getLastNameField(driver);
            WebElement phoneNumber = AddressPage.getMobileElement(driver);
            WebElement houseNumber = AddressPage.getHouseField(driver);
            WebElement streetName = AddressPage.getStreetField(driver);
            WebElement landmark = AddressPage.getLandmarkField(driver);
            WebElement email = null;
            if (isNewUser) {
                email = AddressPage.getEmailField(driver);
            }
            WebElement pincode = AddressPage.getPincodeField(driver);
            WebElement city = AddressPage.getCityField(driver);
            WebElement state = AddressPage.getStateField(driver);
            WebElement saveAddress = AddressPage.getProceedButton(driver);
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
            WebElement addressType = AddressPage.getAddressFieldType(randomAddressType,driver);
            addressType.click();
            WebElement defaultAddress = AddressPage.getDefaultAddressCheckbox(driver);
            defaultAddress.click();
            assertEquals(city.getText().toLowerCase(), CITY.toLowerCase());
            assertEquals(state.getText().toLowerCase(), STATE.toLowerCase());
            saveAddress.click();
            Thread.sleep(2000);
            recordResult("updateAddress", "Pass");

        } catch (AssertionError e) {
            recordResult("updateAddress", "Fail " + e.getMessage());
            Assert.fail("updateAddress assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("updateAddress", "Fail " + e.getMessage());
            Assert.fail("updateAddress failed: " + e.getMessage());
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
            recordResult("changeDefaultAddress", "Pass");

        } catch (AssertionError e) {
            recordResult("changeDefaultAddress", "Fail " + e.getMessage());
            Assert.fail("changeDefaultAddress assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("changeDefaultAddress", "Fail " + e.getMessage());
            Assert.fail("changeDefaultAddress failed: " + e.getMessage());
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
            recordResult("deleteAddress", "Pass");

        } catch (AssertionError e) {
            recordResult("deleteAddress", "Fail " + e.getMessage());
            Assert.fail("deleteAddress assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("copyOtp", "Fail " + e.getMessage());
            Assert.fail("deleteAddress failed: " + e.getMessage());
        }
    }

    //    @Test(priority = 0)
    public void signUp() {
        WebElement mobileInput = null;
        WebElement proceedBtn = null;
        WebElement otpContainer = null;
        WebElement otpLabel = null;
        WebElement mobileNumberLabel = null;
        WebElement mobileNumberEditCta = null;
        WebElement otpEnterInput = null;
        WebElement chooseGender = null;

        try {
            mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull(mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(Utility.getNumber());
            proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

//            otpContainer = OtpPage.getOtpContainer(driver);
//            assertNotEquals(otpContainer, null);
//            otpLabel = OtpPage.getOtpLabel(driver);
//            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
//            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
//            otpEnterInput = OtpPage.getOtpEnterInput(driver);
//            assertNotNull(otpLabel);
//            assertNotNull(mobileNumberLabel);
//            assertNotNull(mobileNumberEditCta);
//            assertNotNull(otpEnterInput);

            WebElement maleElement = SignUpPage.getMaleGenderOption(driver);
            WebElement femaleElement = SignUpPage.getFemaleGenderOption(driver);
            chooseGender = (Utility.RANDOMNUMBER == 0) ? maleElement : femaleElement;
            chooseGender.click();

            SignUpPage.getMonthSpinner(driver).click();
            SignUpPage.getMarchMonthOption(driver).click();

            SignUpPage.getYearSpinner(driver).click();
            SignUpPage.getYear2009Option(driver).click();

            SignUpPage.getReferralInput(driver).click();
            SignUpPage.getReferralInput(driver).sendKeys(SignUpPage.GROUP_INVITE_CODE);

            SignUpPage.getReferralApplyBtn(driver).click();

            // Wait for referral success title to be visible
            SignUpPage.getReferralSuccessTitle(driver);
            assertNotNull(SignUpPage.getReferralSuccessTitle(driver));
            System.out.println(SignUpPage.getReferralSuccessTitle(driver).getText());

            System.out.println(SignUpPage.getReferralSuccessPaymentTitle(driver).getText());

            SignUpPage.getConfirmBtn(driver).click();
            recordResult("SignUp", "Pass");
        } catch (AssertionError e) {
            recordResult("signUp", "Fail " + e.getMessage());
            Assert.fail("singUp assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signUp", "Fail " + e.getMessage());
            Assert.fail("signup failed: " + e.getMessage());
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
            recordResult("addMultipleAddress", "Pass");

        } catch (AssertionError e) {
            recordResult("addMultipleAddress", "Fail " + e.getMessage());
            Assert.fail("addMultipleAddress assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("addMultipleAddress", "Fail " + e.getMessage());
            Assert.fail("addMultipleAddress failed: " + e.getMessage());
        }
        addressEntryCount++;
    }

}
