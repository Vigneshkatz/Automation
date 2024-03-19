package payment;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
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
import org.smytten.pof.payment.AllPaymentPage;
import org.smytten.pof.payment.CardPaymentPage;
import org.smytten.pof.payment.PaymentPage;
import org.smytten.pof.payment.RazorpayPaymentStatusPage;
import org.smytten.pof.product.ShopFrontPage;
import org.smytten.pof.product.TrialProductCard;
import org.smytten.util.Utility;
import org.smytten.util.contants.Bank;
import org.smytten.util.payment.PaymentHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.testng.AssertJUnit.*;

public class TrialPaymentTest extends BaseTest {
    boolean isSignup = true;
    boolean isPrepaid = false;
    private boolean isAppOpen = true;

    @BeforeMethod
    public void goToPaymentPage() {
        initialLandingPageText(isAppOpen);
        testSignUp();
        gotoTrialFront();
        openProductListing();
        addProductToCart();
        gotoCart();
        openPaymentPage();
        updateAddress();
    }

    @Test(priority = 0)
    public void testCod() {
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
            fail("Cod Test Fail " + e.getMessage());
        }
    }

    @Test(priority = 1)
    public void testNetBanking() {
        this.isPrepaid = true;
        try {
            WebElement allOption = PaymentPage.getAllPaymentsOption(driver);
            assertNotNull(allOption);
            allOption.click();
            PaymentPage.getProceedBtn(driver).click();
            if (VerifyElementHelper.isPaymentConsentPopupPresent(driver)) {
                PopUp.getRightCtaConsentPopUp(driver).click();
            }
            Thread.sleep(5000);
            WebElement netBanking = AllPaymentPage.netBankingOption(driver);
            assertNotNull(netBanking);
            netBanking.click();
            WebElement bankChoice = AllPaymentPage.bankOption(driver, Bank.ICICI.getValue());
            assertNotNull(bankChoice);
            bankChoice.click();
            int x = 689, y = 2396;
            touchAction.tap(PointOption.point(x, y)).perform();
            Thread.sleep(5000);
            RazorpayPaymentStatusPage.getSuccessButton(driver).click();
//            WebElement proceedBtn = AllPaymentPage.proceedPaymentBtn(driver);
//            assertNotNull(proceedBtn);
//            proceedBtn.click();
        } catch (AssertionError | Exception e) {
            fail("Net Banking failed" + e.getMessage());
        }
    }

    //    @Test(priority = 2)
    public void testCardPayment() {
        try {
            WebElement allOption = PaymentPage.getAllPaymentsOption(driver);
            assertNotNull(allOption);
            allOption.click();
            PaymentPage.getProceedBtn(driver).click();
            if (VerifyElementHelper.isPaymentConsentPopupPresent(driver)) {
                PopUp.getRightCtaConsentPopUp(driver).click();
            }
            Thread.sleep(5000);
            WebElement cartOption = AllPaymentPage.cardOption(driver);
            assertNotNull(cartOption);
            cartOption.click();
            WebElement cardNumber = CardPaymentPage.cardNumber(driver);
            WebElement cardExpiry = CardPaymentPage.cardExpiry(driver);
            WebElement cardCvv = CardPaymentPage.cardCvv(driver);
            assertNotNull(cardExpiry);
            assertNotNull(cardCvv);
            assertNotNull(cardNumber);
            androidHelper.enterValue(cardNumber, PaymentHelper.VISA_NUMBER);
            androidHelper.enterValue(cardExpiry, PaymentHelper.EXPIRY);
            androidHelper.enterValue(cardCvv, PaymentHelper.CVV);
            int x = 689, y = 2396;
            touchAction.tap(PointOption.point(x, y)).perform();
            RazorpayPaymentStatusPage.getSuccessButton(driver).click();
        } catch (AssertionError | Exception e) {
            fail("Card failed " + e.getMessage());
        }
    }

    //    @Test(priority = 4)
    public void testPaytm() {
        try {
            WebElement allOption = PaymentPage.getAllPaymentsOption(driver);
            assertNotNull(allOption);
            allOption.click();
            PaymentPage.getProceedBtn(driver).click();
            if (VerifyElementHelper.isPaymentConsentPopupPresent(driver)) {
                PopUp.getRightCtaConsentPopUp(driver).click();
            }
            Thread.sleep(5000);
            WebElement upiOption = AllPaymentPage.upiOption(driver);
            assertNotNull(upiOption);
            upiOption.click();
            WebElement upiInputBox = driver.findElement(AppiumBy.id("vpa-upi"));
            assertNotNull(upiInputBox);
            androidHelper.enterValue(upiInputBox, PaymentHelper.UPI_ID);
            WebElement proceedBtn = AllPaymentPage.proceedPaymentBtn(driver);
            assertNotNull(proceedBtn);
            proceedBtn.click();
        } catch (AssertionError | Exception e) {
            fail("Cod Test Fail " + e.getMessage());
        }
    }

    @AfterMethod
    public void verifyOrderDetail() {
        try {
            WebElement orderDetail = TrialOrderConfirmation.getViewOrderDetailButton(driver);
            orderDetail.click();
            assertNotNull(orderDetail);
        } catch (AssertionError | Exception e) {
            fail("goToOrderDetailPage " + e.getMessage());
        }
        androidHelper.back();
        TrialOrderConfirmation.getCloseConfirmationScreen(driver).click();
        shopFrontPopupCheck();
        signOut();
    }

    private void shopFrontPopupCheck() {
        if (!isPrepaid) {
            return;
        }
        try {
            if (VerifyElementHelper.isAppWidePopUpPresent(driver)) {
                PopUp.getPopUpClose(driver).click();
            }
            WebElement walletPopUp = ShopFrontPage.getWalletPopup(driver);
            assertNotNull(walletPopUp);
            WebElement arrowDown = ShopFrontPage.getWalletPopupArrowDown(driver);
            assertNotNull(arrowDown);
            arrowDown.click();
        } catch (AssertionError | Exception e) {
            System.out.println("line -> " + Utility.getCurrentLineNo() + "shopFront popup");
        }
    }


    public void initialLandingPageText(boolean isAppOpen) {
        if (isAppOpen) {
            this.isAppOpen = false;
            try {
                WebElement startCta = LandingPage.getStartCtaElement(driver);
                assertNotNull(startCta);
                startCta.click();
            } catch (AssertionError | Exception e) {
                fail("landing page" + e.getMessage());
            }
        }
    }

    public void testSignUp() {
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
                isAppOpen = false;
                testSignUp();
            }
        } catch (AssertionError | Exception e) {
            fail("signUp" + e.getMessage());
        }
    }

    public void gotoTrialFront() {
        if (VerifyElementHelper.isAppWidePopUpPresent(driver)) {
            PopUp.getPopUpClose(driver).click();
        }

        try {
            WebElement trialFront = Navigation.getTrialHomeTab(driver);
            assertNotNull(trialFront);
            trialFront.click();
        } catch (AssertionError | Exception e) {
            fail("gotoTrialFront failed: " + e.getMessage());
        }
    }

    public void openProductListing() {
        try {
            List<WebElement> list = driver.findElements(AppiumBy.id("com.app.smytten.debug:id/iv_circle_view"));
            assertNotNull(list);
            System.out.println("line -> " + Utility.getCurrentLineNo() + list.size());
            for (int i = 0; i < list.size(); i++) {
                if (i == 1) {
                    list.get(i).click();
                    break;
                }
            }
        } catch (AssertionError | Exception e) {
            fail("failed to open product listing" + e.getMessage());
        }
    }

    public void addProductToCart() {
        try {
            List<WebElement> productCartList = TrialProductCard.getAllProductCard(driver);
            assertNotNull(productCartList);
            for (WebElement productCart : productCartList) {
                productCart.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_try_now")).click();
                try {
                    if (VerifyElementHelper.isProductConsentPopupPresent(driver)) {
                        TrialProductCard.getPopUpDesc(driver);
                        TrialProductCard.getPopUpOkButton(driver);
                    }
                } catch (Exception e) {
                    System.out.println("line -> " + Utility.getCurrentLineNo() + " No pop-up found");
                }
                break;
            }
        } catch (AssertionError | Exception e) {
            fail("failed to open product listing" + e.getMessage());
        }
    }

    public void gotoCart() {
        try {
            WebElement cart = Navigation.getCartView(driver);
            assertNotNull(cart);
            cart.click();
            try {
                if (VerifyElementHelper.isAutoApplyCouponPresent(driver)) {
                    touchAction.tap(PointOption.point(877, 877)).perform();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (AssertionError | Exception e) {
            fail("failed to gotoCart" + e.getMessage());
        }
    }

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
            fail("updateAddress assertion failed: " + e.getMessage());
        }
    }

    public void openPaymentPage() {
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
            fail("failed to open product listing" + e.getMessage());
        }
    }
}
