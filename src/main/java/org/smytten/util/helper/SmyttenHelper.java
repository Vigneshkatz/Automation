package org.smytten.util.helper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.account.AddressPage;
import org.smytten.pof.cart.CartPage;
import org.smytten.pof.cart.TrialOrderConfirmation;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.pof.payment.PaymentPage;
import org.smytten.util.Utility;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.testng.AssertJUnit.*;

public class SmyttenHelper {
    AndroidDriver driver;
    AndroidHelper androidHelper;
    TouchAction touchAction;

    public SmyttenHelper(AndroidDriver driver, AndroidHelper androidHelper, TouchAction touchAction) {
        this.androidHelper = androidHelper;
        this.driver = driver;
        this.touchAction = touchAction;
    }

    public void signOut() throws AssertionError, NoSuchElementException, Exception {
        try {
            checkPopUp();
            gotoAccountPage();
            androidHelper.scrollToBottom();
            WebElement signOut = AccountPage.getSignOut(driver);
            assertNotNull("Sign out button not found", signOut);
            signOut.click();

            WebElement yesCta = driver.findElement(AppiumBy.id("android:id/button1"));
            assertNotNull("Yes button not found", yesCta);
            yesCta.click();
        } catch (AssertionError | Exception e) {
            throw e;
        }
    }

    public void checkPopUp() throws AssertionError, NoSuchElementException, Exception {
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
            throw e;
        }
    }

    public void gotoAccountPage() throws AssertionError, NoSuchElementException, Exception {
        try {
            WebElement profileHomeTab = Navigation.getProfileHomeTab(driver);
            assertNotNull("Profile home tab not found", profileHomeTab);
            profileHomeTab.click();
        } catch (AssertionError e) {
            throw e;
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    public void signUpHelper() throws AssertionError, NoSuchElementException, Exception {
        try {
            WebElement maleElement = SignUpPage.getMaleGenderOption(driver);
            WebElement femaleElement = SignUpPage.getFemaleGenderOption(driver);
            WebElement chooseGender = (Utility.RANDOMNUMBER == 0) ? maleElement : femaleElement;
            chooseGender.click();
            SignUpPage.getMonthSpinner(driver).click();
            SignUpPage.getMarchMonthOption(driver).click();
            SignUpPage.getYearSpinner(driver).click();
            SignUpPage.getYear2009Option(driver).click();
            WebElement referralInput = SignUpPage.getReferralInput(driver);
            referralInput.click();
            referralInput.sendKeys(SignUpPage.GROUP_INVITE_CODE);
            SignUpPage.getReferralApplyBtn(driver).click();
            WebElement referralSuccessTitle = SignUpPage.getReferralSuccessTitle(driver);
            assertNotNull("Referral success title not found", referralSuccessTitle);
            System.out.println(referralSuccessTitle.getText());
            WebElement referralSuccessPaymentTitle = SignUpPage.getReferralSuccessPaymentTitle(driver);
            System.out.println(referralSuccessPaymentTitle.getText());
            SignUpPage.getConfirmBtn(driver).click();
        } catch (Exception e) {
            throw e;
        }
    }

    public void gotoTrialFront() throws AssertionError, NoSuchElementException, Exception {
        if (VerifyElementHelper.isAppWidePopUpPresent(driver)) {
            PopUp.getPopUpClose(driver).click();
        }
        try {
            WebElement trialFront = Navigation.getTrialHomeTab(driver);
            assertNotNull(trialFront);
            trialFront.click();
        } catch (AssertionError | Exception e) {
            throw e;
        }
    }

    public void gotoCart() throws AssertionError, NoSuchElementException, Exception {
        try {
            WebElement cart = Navigation.getCartView(driver);
            assertNotNull(cart);
            cart.click();
            checkAutoApplyCoupon();
        } catch (AssertionError | Exception e) {
            throw e;
        }
    }

    public void openPaymentPage() throws AssertionError, NoSuchElementException, Exception {
        try {
            WebElement proceedBtn = CartPage.getProceedButton(driver);
            assertNotNull(proceedBtn);
            proceedBtn.click();
            try {
                if (VerifyElementHelper.isPaymentConsentPopupPresent(driver)) {
                    PopUp.getRightCtaConsentPopUp(driver).click();
                }
            } catch (Exception e) {
                System.out.println("line -> " + Utility.getCurrentLineNo() + " No consent popUp found");
            }

        } catch (AssertionError | Exception e) {
            throw e;

        }
    }

    public void updateAddress() throws AssertionError, NoSuchElementException, Exception {
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

            androidHelper.clearAndSetValueInField(firstName, Utility.generateRandomString(8));
            androidHelper.clearAndSetValueInField(lastName, Utility.generateRandomString(8));
            androidHelper.clearAndSetValueInField(phoneNumber, "9500752205 ");
            androidHelper.clearAndSetValueInField(email, Utility.generateRandomEmail());
            androidHelper.clearAndSetValueInField(houseNumber, Utility.generateRandomString(8));
            androidHelper.clearAndSetValueInField(streetName, Utility.generateRandomString(8));
            androidHelper.clearAndSetValueInField(pincode, AddressPage.PINCODE);
            androidHelper.clearAndSetValueInField(landmark, Utility.generateRandomString(8));

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
            throw e;
        }
    }

    public void signUp(boolean isSignup, boolean isAppOpen) throws AssertionError, NoSuchElementException, Exception {
        try {
            // Enter mobile number and proceed to OTP
            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile number input field not found", mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(Utility.getNumber());

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();
            Thread.sleep(5000);
            if (!(VerifyElementHelper.isSignUpPopUpPresent(driver))) {
                isSignup = false;
            }

            if (isSignup) {
                signUpHelper();
            } else {
                if (VerifyElementHelper.isAppWidePopUpPresent(driver)) {
                    PopUp.getPopUpClose(driver).click();
                }
                signOut();
                signUp(true, false);
            }
        } catch (AssertionError | Exception e) {
            throw e;
        }
    }

    public void initiateSignUp() throws AssertionError, NoSuchElementException, Exception {
        openLoginPage();
        signUp(true, true);
    }

    private void openLoginPage() throws AssertionError, NoSuchElementException, Exception {
        try {
            touchAction = new TouchAction<>(driver);
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            startCta.click();

        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public void openShopFront() throws AssertionError, NoSuchElementException, Exception {
        try {
            WebElement shopHomeTab = Navigation.getShopHomeTab(driver);
            assertNotNull(shopHomeTab);
            shopHomeTab.click();
            checkPopUp();
        } catch (AssertionError e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean checkAutoApplyCoupon() {
        try {
            if (VerifyElementHelper.isAutoApplyCouponPresent(driver)) {
                touchAction.tap(PointOption.point(877, 877)).perform();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void placeCodOrder() throws AssertionError, NoSuchElementException, Exception {
        try {
            WebElement codOption = PaymentPage.getCodOption(driver);
            assertNotNull(codOption);
            codOption.click();
            PaymentPage.getProceedBtn(driver).click();
            try {
                if (VerifyElementHelper.isCodConsentPopUpPresent(driver)) {
                    PopUp.getCodProceedBtn(driver).click();
                }
            } catch (Exception e) {
                System.out.println("line -> " + Utility.getCurrentLineNo() + " No consent popup");
            }
        } catch (AssertionError | Exception e) {
            throw e;
        }
    }

    public void openOrderDetailPageFromOrderConfirmationPage() throws AssertionError, NoSuchElementException, Exception {
        try {
            WebElement orderDetail = TrialOrderConfirmation.getViewOrderDetailButton(driver);
            orderDetail.click();
            assertNotNull(orderDetail);
        } catch (Exception e) {
            throw e;
        }
    }
}
