package luxe;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AddressPage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.pof.luxe.LuxeLandingPage;
import org.smytten.pof.luxe.LuxeOrderConfirmation;
import org.smytten.pof.payment.RazorpayPaymentStatusPage;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import java.util.Random;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class LuxeMemberShipTest extends BaseTest {
    public static LuxeLandingPage luxeLandingPage = null;
    public static OtpPage otpPage = null;
    public static LoginPage loginPage = null;
    public static SignUpPage signUpPage = null;
    public static LuxeOrderConfirmation luxeOrderConfirmation = null;
    public static Navigation navigation = null;
    public static AddressPage addressPage;
    private static PopUp popUp;
    private static TouchAction touchAction;
    private static RazorpayPaymentStatusPage razorpayPaymentStatusPage;


    @Test(priority = 0)
    public void signUp() throws InterruptedException {
        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        WebElement mobileInput = null;
        WebElement proceedBtn = null;
        WebElement otpContainer = null;
        WebElement otpLabel = null;
        WebElement mobileNumberLabel = null;
        WebElement mobileNumberEditCta = null;
        WebElement otpEnterInput = null;
        WebElement chooseGender = null;
        WebElement maleElement = null;
        WebElement femaleElement = null;

        try {
            mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull(mobileInput);
            mobileInput.click();
            mobileInput.sendKeys("9500752205");
            proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            otpContainer = OtpPage.getOtpContainer(driver);
            assertNotEquals(otpContainer, null);
            otpLabel = OtpPage.getOtpLabel(driver);
            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            otpEnterInput = OtpPage.getOtpEnterInput(driver);
            assertNotNull(otpLabel);
            assertNotNull(mobileNumberLabel);
            assertNotNull(mobileNumberEditCta);
            assertNotNull(otpEnterInput);

            maleElement = SignUpPage.getMaleGenderOption(driver);
            femaleElement = SignUpPage.getFemaleGenderOption(driver);
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
            assertNotNull(SignUpPage.getReferralSuccessTitle(driver));
            System.out.println(SignUpPage.getReferralSuccessTitle(driver).getText());

            System.out.println(SignUpPage.getReferralSuccessPaymentTitle(driver).getText());

            SignUpPage.getConfirmBtn(driver).click();
        } catch (Exception e) {
            fail("Some element not found");
        }
    }


    @Test(priority = 1)
    public void checkPopUp() {
        WebElement popUpClose = null;
        try {
            popUpClose = PopUp.getPopUpClose(driver);
            popUpClose.click();
            assertTrue("popup successfully closed", true);
        } catch (Exception e) {
            System.out.println("no popUp");
            assertTrue("popup is not there", true);
        }
    }

    @Test(priority = 2)
    public void gotoTrialFront() throws InterruptedException {
        WebElement popUpClose = null;
        try {
            popUp = new PopUp(driver);
            WebElement trialFront = Navigation.getTrialHomeTab(driver);
            assertNotNull(trialFront);
            trialFront.click();
            try {
                popUpClose = PopUp.getPopUpClose(driver);
                popUpClose.click();
            } catch (Exception e) {
                System.out.println("no popUp");
            }
            Thread.sleep(2000);
        } catch (Exception e) {
            fail("trial front not found");
        }
    }

    @Test(priority = 3)
    public void openLuxeMembershipPage() throws InterruptedException {
        WebElement luxeEntryPointTrial = null;
        WebElement luxeTitle = null;
        WebElement luxeProceed = null;
        try {
            luxeEntryPointTrial = LuxeLandingPage.getLuxeTopImageView(driver);
            luxeEntryPointTrial.click();
            luxeTitle = LuxeLandingPage.getLuxeTitle(driver);
            String luxeTitleText = luxeTitle.getText();
            assertEquals("Luxe title", luxeLandingPage.getLuxeTitleText(), luxeTitleText.trim());
            luxeProceed = LuxeLandingPage.getProceedButton(driver);
            luxeProceed.click();
            Thread.sleep(2000);
            updateAddress();
            completePayment();
            verifyOrderConfirmScreen();
        } catch (Exception e) {
            fail("open luxe membership failed");
        }
    }

    @Test(priority = 4)
    public void updateAddress() {
        WebElement firstName = null;
        WebElement lastName = null;
        WebElement phoneNumber = null;
        WebElement houseNumber = null;
        WebElement streetName = null;
        WebElement landmark = null;
        WebElement email = null;
        WebElement pincode = null;
        WebElement city = null;
        WebElement state = null;
        WebElement saveAddress = null;
        WebElement addressType = null;
        WebElement defaultAddress = null;

        try {
            firstName = AddressPage.getFirstNameField(driver);
            lastName = AddressPage.getLastNameField(driver);
            phoneNumber = AddressPage.getMobileElement(driver);
            houseNumber = AddressPage.getHouseField(driver);
            streetName = AddressPage.getStreetField(driver);
            landmark = AddressPage.getLandmarkField(driver);
            email = AddressPage.getEmailField(driver);
            pincode = AddressPage.getPincodeField(driver);
            city = AddressPage.getCityField(driver);
            state = AddressPage.getStateField(driver);
            saveAddress = AddressPage.getProceedButton(driver);

            firstName.click();
            firstName.clear();
            firstName.sendKeys(AddressPage.FIRST_NAME);
            driver.navigate().back();
            lastName.click();
            lastName.clear();
            lastName.sendKeys(AddressPage.LAST_NAME);
            driver.navigate().back();
            phoneNumber.click();
            phoneNumber.clear();
            phoneNumber.sendKeys(Utility.getNumber());
            driver.navigate().back();
            email.click();
            email.clear();
            email.sendKeys(Utility.generateRandomEmail());
            houseNumber.click();
            houseNumber.sendKeys(AddressPage.HOUSE_NO);
            driver.navigate().back();
            streetName.click();
            streetName.sendKeys(AddressPage.STREET);
            driver.navigate().back();
            pincode.click();
            pincode.clear();
            pincode.sendKeys(AddressPage.PINCODE);
            driver.navigate().back();
            landmark.click();
            landmark.sendKeys(AddressPage.LANDMARK);
            driver.navigate().back();
            Random random = new Random();
            int randomIndex = random.nextInt(AddressPage.ADDRESS_TYPE.length);
            String randomAddressType = AddressPage.ADDRESS_TYPE[randomIndex];
            addressType = AddressPage.getAddressFieldType(randomAddressType, driver);
            addressType.click();
            defaultAddress = AddressPage.getDefaultAddressCheckbox(driver);
            defaultAddress.click();
            assertEquals(city.getText().toLowerCase(), AddressPage.CITY.toLowerCase());
            assertEquals(state.getText().toLowerCase(), AddressPage.STATE.toLowerCase());
            saveAddress.click();
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
            fail("update address failed");
        }
    }

    @Test(priority = 5)
    private void completePayment() throws InterruptedException {
        WebElement paymentTitle = null;
        WebElement upi = null;
        WebElement proceedPayment = null;
        WebElement idbiBank = null;
        WebElement successPage = null;
        WebElement successCta = null;
        WebElement orderConfirmScreen = null;

        try {
            paymentTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_payment_mode"));
            assertEquals("payment title", "Select Payment Methods", paymentTitle.getText());
            upi = driver.findElement(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id=\"com.app.smytten.debug:id/rv_payment_mode\"]/android.widget.LinearLayout[1]"));
            upi.click();
            proceedPayment = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
            proceedPayment.click();
            Thread.sleep(5000);

            touchAction.tap(PointOption.point(625, 827)).perform();
            Thread.sleep(2000);
            idbiBank = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"bank-item-IBKL\"]/android.view.View"));
            idbiBank.click();
            Thread.sleep(1000);
            touchAction.tap(PointOption.point(544, 2297)).perform();
            Thread.sleep(10000);
            successPage = driver.findElement(AppiumBy.xpath("//android.webkit.WebView[@text=\"Razorpay Bank\"]"));
            assertNotEquals(null, successPage);
            successCta = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Success\"]"));
            successCta.click();
            Thread.sleep(5000);

            orderConfirmScreen = LuxeOrderConfirmation.getRootLayout(driver);
            assertNotEquals(null, orderConfirmScreen);
        } catch (Exception e) {
            fail("payment failed");
        }
    }


    @Test(priority = 6)
    private void verifyOrderConfirmScreen() throws InterruptedException {
        WebElement fullPage = null;
        WebElement header = null;
        WebElement title = null;
        WebElement orderId = null;
        WebElement edd = null;
        WebElement membershipValidity = null;
        WebElement totalPaid = null;
        WebElement luxeBanner = null;
        WebElement helpSection = null;
        WebElement exploreCta = null;
        try {
            fullPage = LuxeOrderConfirmation.getRootLayout(driver);
            header = LuxeOrderConfirmation.getHeaderImageView(driver);
            title = LuxeOrderConfirmation.getSizeTextView(driver);
            orderId = LuxeOrderConfirmation.getOrderIdTextView(driver);
            edd = LuxeOrderConfirmation.getEstimatedDeliveryDateTextView(driver);
            membershipValidity = LuxeOrderConfirmation.getTotalPayableTextView(driver);
            totalPaid = LuxeOrderConfirmation.getTotalPayableRsTextView(driver);
            luxeBanner = LuxeOrderConfirmation.getMembershipBannerImageView(driver);
            helpSection = LuxeOrderConfirmation.getTermsAndConditionsTextView(driver);
            exploreCta = LuxeOrderConfirmation.getExploreButton(driver);
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
        } catch (Exception e) {
            fail("Some element not found");
        }
    }
}
