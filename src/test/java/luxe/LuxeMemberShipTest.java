package luxe;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AddressPage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.pof.luxe.LuxeLandingPage;
import org.smytten.pof.luxe.LuxeOrderConfirmation;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.AssertJUnit.*;


public class LuxeMemberShipTest extends BaseTest {
    private static TouchAction touchAction;

    @Test(priority = 0)
    public void verifyStartCtaOnInitialLandingPage() {
        try {
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull("Start CTA element not found", startCta);
            startCta.click();
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

            // Fill in address details
            firstName.click();
            firstName.clear();
            firstName.sendKeys(AddressPage.FIRST_NAME);
            lastName.click();
            lastName.clear();
            lastName.sendKeys(AddressPage.LAST_NAME);
            phoneNumber.click();
            phoneNumber.clear();
            phoneNumber.sendKeys(Utility.getNumber());
            email.click();
            email.clear();
            email.sendKeys(Utility.generateRandomEmail());
            houseNumber.click();
            houseNumber.sendKeys(AddressPage.HOUSE_NO);
            streetName.click();
            streetName.sendKeys(AddressPage.STREET);
            pincode.click();
            pincode.clear();
            pincode.sendKeys(AddressPage.PINCODE);
            landmark.click();
            landmark.sendKeys(AddressPage.LANDMARK);

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

            touchAction.tap(PointOption.point(625, 827)).perform();
            Thread.sleep(2000);

            WebElement idbiBankOption = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"bank-item-IBKL\"]/android.view.View"));
            idbiBankOption.click();
            Thread.sleep(1000);

            touchAction.tap(PointOption.point(544, 2297)).perform();
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
    public void verifyOrderConfirmScreen() throws InterruptedException {
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
