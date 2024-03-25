package luxe;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AddressPage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.luxe.LuxeLandingPage;
import org.smytten.pof.luxe.LuxeOrderConfirmation;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.AssertJUnit.*;


public class LuxeMemberShipTest extends BaseTest {

    @Test(priority = 0)
    public void verifyStartCtaOnInitialLandingPage() {
        try {
           smyttenHelper.openLoginPage();
        } catch (AssertionError | Exception e) {
            fail("verifyStartCtaOnInitialLandingPage " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void testSignUp() {
        try {
            // Enter mobile number and proceed to OTP
            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile number input field not found", mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(Utility.getNumber());

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();
            smyttenHelper.signUpHelper();
        } catch (AssertionError | Exception e) {
            fail("signUp" + e.getMessage());
        }
    }

    @Test(priority = 2)
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

    @Test(priority = 3)
    public void testGotoTrialFront() {
        try {
            WebElement trialFrontTab = Navigation.getTrialHomeTab(driver);
            assertNotNull("Trial home tab element not found", trialFrontTab);
            trialFrontTab.click();
        } catch (AssertionError | Exception e) {
            fail("testGotoTrialFront" + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void testOpenLuxeMembershipPage() {
        try {
            WebElement luxeEntryPointTrial = LuxeLandingPage.getLuxeTopImageView(driver);
            assertNotNull("Luxe entry point trial element not found", luxeEntryPointTrial);
            luxeEntryPointTrial.click();

            WebElement luxeTitle = LuxeLandingPage.getLuxeTitle(driver);
            String actualLuxeTitle = luxeTitle.getText().toLowerCase().trim();
            String expectedLuxeTitle = LuxeLandingPage.getLuxeTitleText().toLowerCase().trim();
            assertEquals("Luxe title", expectedLuxeTitle, actualLuxeTitle);

            WebElement luxeProceed = LuxeLandingPage.getProceedButton(driver);
            assertNotNull("Luxe proceed button not found", luxeProceed);
            luxeProceed.click();
        } catch (AssertionError e) {
            String errorMessage = "testOpenLuxeMembershipPage assertion failed: " + e.getMessage();
            fail(errorMessage);
        } catch (Exception e) {
            String errorMessage = "testOpenLuxeMembershipPage failed: " + e.getMessage();
            fail(errorMessage);
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
            WebElement email = AddressPage.getEmailField(driver);
            WebElement pincode = AddressPage.getPincodeField(driver);
            WebElement city = AddressPage.getCityField(driver);
            WebElement state = AddressPage.getStateField(driver);
            WebElement saveAddress = AddressPage.getProceedButton(driver);
            WebElement addressType;
            WebElement defaultAddress;

            androidHelper.clearAndSetValueInField(firstName, AddressPage.FIRST_NAME);
            androidHelper.clearAndSetValueInField(lastName, AddressPage.LAST_NAME);
            androidHelper.clearAndSetValueInField(phoneNumber, "9500752205 ");
            androidHelper.clearAndSetValueInField(email, Utility.generateRandomEmail());
            androidHelper.clearAndSetValueInField(houseNumber, AddressPage.HOUSE_NO);
            androidHelper.clearAndSetValueInField(streetName, AddressPage.STREET);
            androidHelper.clearAndSetValueInField(pincode, AddressPage.PINCODE);
            androidHelper.clearAndSetValueInField(landmark, AddressPage.LANDMARK);


            // Select random address type and set as default
            Random random = new Random();
            int randomIndex = random.nextInt(AddressPage.ADDRESS_TYPE.length);
            String randomAddressType = AddressPage.ADDRESS_TYPE[randomIndex];
            addressType = AddressPage.getAddressFieldType(randomAddressType, driver);
            addressType.click();
            defaultAddress = AddressPage.getDefaultAddressCheckbox(driver);
            defaultAddress.click();

            // Verify city and state
            assertEquals("City mismatch", AddressPage.CITY.toLowerCase(), city.getText().toLowerCase());
            assertEquals("State mismatch", AddressPage.STATE.toLowerCase(), state.getText().toLowerCase());

            // Save address
            saveAddress.click();
            Thread.sleep(2000);
        } catch (AssertionError | Exception e) {
            fail("testUpdateAddress" + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void completePayment() {
        try {
            WebElement paymentTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_payment_mode"));
            assertEquals("Payment title", "Select Payment Methods", paymentTitle.getText());

            WebElement upiPaymentMethod = driver.findElement(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.app.smytten.debug:id/rv_payment_mode\"]/android.widget.LinearLayout[1]"));
            upiPaymentMethod.click();

            WebElement proceedButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
            proceedButton.click();
            Thread.sleep(5000);

            smyttenHelper.tap(625, 827);
            Thread.sleep(2000);

            WebElement idbiBankOption = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"bank-item-IBKL\"]/android.view.View"));
            idbiBankOption.click();
            Thread.sleep(1000);

            smyttenHelper.tap(544, 2297);
            Thread.sleep(10000);

            WebElement successPage = driver.findElement(AppiumBy.xpath("//android.webkit.WebView[@text=\"Razorpay Bank\"]"));
            assertNotNull("Success page not found", successPage);

            WebElement successButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Success\"]"));
            successButton.click();
            Thread.sleep(5000);

            WebElement orderConfirmationScreen = LuxeOrderConfirmation.getRootLayout(driver);
            assertNotNull("Order confirmation screen not found", orderConfirmationScreen);

        } catch (AssertionError | InterruptedException e) {
            fail("completePayment " + e.getMessage());
        }
    }

    @Test(priority = 7)
    public void verifyOrderConfirmScreen() {
        try {
            WebElement fullPage = LuxeOrderConfirmation.getRootLayout(driver);
            WebElement header = LuxeOrderConfirmation.getHeaderImageView(driver);
            WebElement title = LuxeOrderConfirmation.getSizeTextView(driver);
            WebElement orderId = LuxeOrderConfirmation.getOrderIdTextView(driver);
            WebElement edd = LuxeOrderConfirmation.getEstimatedDeliveryDateTextView(driver);
            WebElement membershipValidity = LuxeOrderConfirmation.getTotalPayableTextView(driver);
            WebElement totalPaid = LuxeOrderConfirmation.getTotalPayableRsTextView(driver);
            WebElement luxeBanner = LuxeOrderConfirmation.getMembershipBannerImageView(driver);
            WebElement helpSection = LuxeOrderConfirmation.getTermsAndConditionsTextView(driver);
            WebElement exploreCta = LuxeOrderConfirmation.getExploreButton(driver);

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

            assertEquals("Luxe Welcome kit (1 item)", title.getText());
            assertEquals("Luxe membership (1 year)", membershipValidity.getText());
            System.out.println(exploreCta.getText());
            exploreCta.click();

            Thread.sleep(10000);
        } catch (AssertionError | Exception e) {
            fail("verifyOrderConfirmScreen " + e.getMessage());
        }
    }

}
