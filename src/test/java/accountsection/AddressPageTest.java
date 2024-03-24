package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.apache.commons.math3.analysis.function.Add;
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

import java.time.Duration;
import java.util.*;

import static org.testng.AssertJUnit.*;


public class AddressPageTest extends BaseTest {
    public static final String[] ADDRESS_TYPE = {"Office", "Home", "Other"};
    public static Map<Integer, PinCodeDetails> pincode = defaultPincode();
    static List<Address> addressList = new ArrayList<>();
    private static int addressEntryCount = 1;
    private Boolean isEmpty = true;

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
        pinCodeMap.put(5, new PinCodeDetails("500001", "Hyderabad", "Andhra Pradesh"));
        return pinCodeMap;
    }


    @Test(priority = 0)
    public void testLoginWithCorrectOTP() {
        try {
            smyttenHelper.initiateSignUp();
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
            fail("gotoAccountPage assertion failed: " + e.getMessage());
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
                assertEquals("sorry, you have no saved address.", noAddressWarning.getText().toLowerCase());
            }
            addNewAddressCta.click();
        } catch (AssertionError | Exception e) {
            fail("verifyAddressPag" + "Address page elements not found. " + e.getMessage());
        }
    }

//    @Test(priority = 5)
//    public void testUpdateAddress() {
//        try {
//            WebElement firstName = AddressPage.getFirstNameField(driver);
//            WebElement lastName = AddressPage.getLastNameField(driver);
//            WebElement phoneNumber = AddressPage.getMobileElement(driver);
//            WebElement houseNumber = AddressPage.getHouseField(driver);
//            WebElement streetName = AddressPage.getStreetField(driver);
//            WebElement landmark = AddressPage.getLandmarkField(driver);
//            WebElement email = null;
//            if (isNewUser) {
//                email = AddressPage.getEmailField(driver);
//                androidHelper.clearAndSetValueInField(email, Utility.generateRandomEmail());
//            }
//            WebElement pincode = AddressPage.getPincodeField(driver);
//            WebElement city = AddressPage.getCityField(driver);
//            WebElement state = AddressPage.getStateField(driver);
//            WebElement saveAddress = AddressPage.getProceedButton(driver);
//
//            // Clear and set values for fields
//            androidHelper.clearAndSetValueInField(firstName, FIRST_NAME);
//            androidHelper.clearAndSetValueInField(lastName, LAST_NAME);
//            androidHelper.clearAndSetValueInField(phoneNumber, MOBILE_NUMBER == null ? "9500752205 " : MOBILE_NUMBER);
//            androidHelper.clearAndSetValueInField(houseNumber, HOUSE_NO);
//            androidHelper.clearAndSetValueInField(streetName, STREET);
//            androidHelper.clearAndSetValueInField(pincode, PINCODE);
//            androidHelper.clearAndSetValueInField(landmark, LANDMARK);
//
//            // Select a random address type
//            Random random = new Random();
//            int randomIndex = random.nextInt(ADDRESS_TYPE.length);
//            String randomAddressType = ADDRESS_TYPE[randomIndex];
//            WebElement addressType = AddressPage.getAddressFieldType(randomAddressType, driver);
//            addressType.click();
//
//            // Set default address checkbox
//            WebElement defaultAddress = AddressPage.getDefaultAddressCheckbox(driver);
//            defaultAddress.click();
//
//            // Verify city and state
//            assertEquals(city.getText().toLowerCase(), CITY.toLowerCase());
//            assertEquals(state.getText().toLowerCase(), STATE.toLowerCase());
//
//            // Click save address button
//            saveAddress.click();
//            Thread.sleep(2000);
//        } catch (AssertionError | Exception e) {
//            fail("updateAddress assertion failed: " + e.getMessage());
//        }
//    }

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
            boolean isFirstAddress = true;
            for (WebElement addressCard : addressCards) {
                try {
                    if (isFirstAddress){
                        isFirstAddress = false;
                    }else {
                        defaultCta = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_set_default"));
                        defaultAddressName = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_title")).getText();
                        defaultCta.click();
                        break;
                    }

                } catch (NoSuchElementException e) {
                    assertTrue("Only one address found and it is already default", true);
                }
            }

            assertEquals("Default address name matches", defaultAddressName, driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_title")).getText());
        } catch (AssertionError | Exception e) {
            fail("changeDefaultAddress assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void deleteAddress() {
        try {
            WebElement addressList = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rv_address"));
            List<WebElement> addressCards = addressList.findElements(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            if (addressCards.isEmpty()) {
                assertTrue("No more addresses left to delete", true);
                return;
            }

            int initialAddressSize = addressCards.size();
            int deletedAddressCount = 0;
            while (deletedAddressCount < initialAddressSize) {
                WebElement addressCard = addressCards.get(0);

                WebElement deleteIcon = addressCard.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_delete"));
                deleteIcon.click();

                WebElement ctaRightElement = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_cta_right"));
                ctaRightElement.click();
                deletedAddressCount++;
                addressCards = addressList.findElements(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
                System.out.println(addressCards.size());
            }
        } catch (NoSuchElementException e) {
            assertTrue("No more addresses left to delete", true);
        } catch (Exception e) {
            fail("Failed to delete address: " + e.getMessage());
        }
    }

    @Test(invocationCount = 5,priority = 5)
    public void addMultipleAddress() throws InterruptedException {
        this.isEmpty = false;
        if (addressEntryCount == 1) {
            addAddress(5);
        }
        try {
            WebElement firstName = AddressPage.getFirstNameField(driver);
            WebElement lastName = AddressPage.getLastNameField(driver);
            WebElement phoneNumber = AddressPage.getMobileElement(driver);
            WebElement houseNumber = AddressPage.getHouseField(driver);
            WebElement email = null;
            if(addressEntryCount == 1) {
                 email = AddressPage.getEmailField(driver);
            }

            WebElement streetName = AddressPage.getStreetField(driver);
            WebElement landmark = AddressPage.getLandmarkField(driver);
            WebElement pincode = AddressPage.getPincodeField(driver);
            WebElement city = AddressPage.getCityField(driver);
            WebElement state = AddressPage.getStateField(driver);
            WebElement saveAddress = AddressPage.getProceedButton(driver);

            androidHelper.clearAndSetValueInField(firstName, addressList.get(addressEntryCount - 1).firstName);
            androidHelper.clearAndSetValueInField(lastName, addressList.get(addressEntryCount - 1).lastName);
            androidHelper.clearAndSetValueInField(phoneNumber, addressList.get(addressEntryCount - 1).phone);
            androidHelper.clearAndSetValueInField(houseNumber, addressList.get(addressEntryCount - 1).houseNumber);
            androidHelper.clearAndSetValueInField(streetName, addressList.get(addressEntryCount - 1).streetName);
            androidHelper.clearAndSetValueInField(pincode, addressList.get(addressEntryCount - 1).pinCode);
            androidHelper.clearAndSetValueInField(landmark, addressList.get(addressEntryCount - 1).landmark);
            if(email != null){
                androidHelper.clearAndSetValueInField(email, addressList.get(addressEntryCount - 1).email);
            }
            Random random = new Random();
            int randomIndex = random.nextInt(ADDRESS_TYPE.length);
            String randomAddressType = ADDRESS_TYPE[randomIndex];
            WebElement addressType = AddressPage.getAddressFieldType(randomAddressType, driver);
            addressType.click();
            if (randomIndex == 1) {
                WebElement defaultAddress = AddressPage.getSetCheckBoxDefaultButton(driver);
                defaultAddress.click();
            }
            assertEquals(city.getText().toLowerCase(), addressList.get(addressEntryCount - 1).city.toLowerCase());
            assertEquals(state.getText().toLowerCase(), addressList.get(addressEntryCount - 1).state.toLowerCase());
            saveAddress.click();
        } catch (AssertionError | Exception e) {
            fail("addMultipleAddress assertion failed: " + e.getMessage());
        }
        addressEntryCount++;
        if(addressEntryCount <= 5){
            WebElement addNewAddressCta = AddressPage.getAddNewAddressLayout(driver);
            addNewAddressCta.click();
        }
    }
}
