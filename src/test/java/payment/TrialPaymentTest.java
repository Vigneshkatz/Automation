package payment;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.cart.TrialOrderConfirmation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
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

import static org.testng.AssertJUnit.*;

public class TrialPaymentTest extends BaseTest {
    boolean isSignup = true;
    boolean isPrepaid = false;
    private boolean isAppOpen = true;

    @BeforeMethod
    public void initialSetUp() {
        try {
            initialLandingPageText(isAppOpen);
            smyttenHelper.signUp(isSignup,isAppOpen);
            smyttenHelper.gotoTrialFront();
            openProductListing();
            addProductToCart();
            smyttenHelper.gotoCart();
            smyttenHelper.openPaymentPage();
            smyttenHelper.updateAddress();
        }catch (Exception e){
            fail("initialSetup fail"+e.getMessage());
        }

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

        androidHelper.back();
        TrialOrderConfirmation.getCloseConfirmationScreen(driver).click();
        shopFrontPopupCheck();
        smyttenHelper.signOut();
        } catch (AssertionError | Exception e) {
            fail("goToOrderDetailPage " + e.getMessage());
        }
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

}
