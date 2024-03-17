package order;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AddressPage;
import org.smytten.pof.cart.CartPage;
import org.smytten.pof.cart.TrialOrderConfirmation;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.pof.payment.Payment;
import org.smytten.pof.product.TrialProductCard;
import org.smytten.util.Utility;
import org.smytten.util.driver.DriverHelper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.testng.AssertJUnit.*;

public class TrialOrderTest extends BaseTest {
    private static final String MOBILE_NUMBER = Utility.getNumber();
    @Test(priority = 0)
    public void initialLandingPageText() {
        try {
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            startCta.click();
        } catch (AssertionError | Exception e) {
            fail("landing page" + e.getMessage());
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
        } catch (AssertionError | Exception e){
            fail("signUp"+ e.getMessage());
        }
    }

    @Test(priority = 3)
    public void gotoTrialFront() {
        try {
            WebElement trialFront = Navigation.getTrialHomeTab(driver);
            assertNotNull(trialFront);
            trialFront.click();
        } catch (AssertionError | Exception e) {
            fail("gotoTrialFront failed: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void openProductListing() {
        try {
            List<WebElement> list = driver.findElements(AppiumBy.id("com.app.smytten.debug:id/iv_circle_view"));
            assertNotNull(list);
            System.out.println(list.size());
            for (int i = 0; i < list.size(); i++) {
                if (i == 1) {
                    list.get(i).click();
                    break;
                }
            }
        }catch (AssertionError | Exception e){
            fail("failed to open product listing"+e.getMessage());
        }


    }

    @Test(priority = 5)
    public void addProductToCart() {
        try {
            List<WebElement> productCartList = TrialProductCard.getAllProductCard(driver);
            assertNotNull(productCartList);
            for (WebElement productCart : productCartList) {
                productCart.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_try_now")).click();
                try {
                    if (VerifyElementHelper.isPopupPresent(driver)) {
                        TrialProductCard.getPopUpDesc(driver);
                        TrialProductCard.getPopUpOkButton(driver);
                    } else {
                        System.out.println("No pop-up found");
                    }
                }catch (NoSuchElementException e)
                {
                    e.printStackTrace();
                }
                break;
            }
        } catch (AssertionError | Exception e){
            fail("failed to open product listing"+e.getMessage());
        }
    }

    @Test(priority = 6)
    public void gotoCart() {
        try {
            WebElement cart = Navigation.getCartView(driver);
            assertNotNull(cart);
            cart.click();
            try {
                if (VerifyElementHelper.isAutoApplyCouponPresent(driver)) {
                    TouchAction touchAction = new TouchAction(driver);
                    touchAction.tap(PointOption.point(877, 877)).perform();
                }
            }catch (NoSuchElementException e){
                e.printStackTrace();
            }
        }catch (AssertionError | Exception e){
            fail("failed to gotoCart"+e.getMessage());
        }
    }

    @Test(priority = 7)
    public void goToPaymentPage() {
        try {
           driverHelper.scrollToBottom();
            WebElement proceedBtn = CartPage.getProceedButton(driver);
            assertNotNull(proceedBtn);
            proceedBtn.click();
            if (VerifyElementHelper.isConsentPopupPresent(driver)) {
                PopUp.getRightCtaConsentPopUp(driver).click();
            }
        }catch (AssertionError | Exception e){
            fail("failed to open product listing"+e.getMessage());
        }
    }

    @Test(priority = 8)
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
        } catch (AssertionError | Exception e) {
            fail("updateAddress assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 9)
    public void codOrder() {
        try {
            WebElement codOption = Payment.getCodOption(driver);
            assertNotNull(codOption);
            codOption.click();
            WebElement proceedBtn = Payment.getProceedBtn(driver);
            assertNotNull(proceedBtn);
            proceedBtn.click();

                if (VerifyElementHelper.isCodCouponPopUpPresent(driver)) {
                    PopUp.getCodProceedBtn(driver).click();
                }
        } catch (AssertionError | Exception e){
            fail("failed to codOrder"+e.getMessage());
        }
    }

    @Test(priority = 10)
    public void goToOrderDetailPage() {
        try {
            WebElement orderDetail = TrialOrderConfirmation.getViewOrderDetailButton(driver);
            orderDetail.click();
            assertNotNull(orderDetail);
        } catch (AssertionError | Exception e){
            fail("goToOrderDetailPage "+ e.getMessage());
        }
    }

}
