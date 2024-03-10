package signin;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.smytten.pof.entry.LandingPage;
import org.testng.annotations.Test;

import java.util.Base64;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.*;


public class LoginTest extends BaseTest {
    private static AccountPage accountPage;
    private static Navigation navigation;
    private static PopUp popUp;
    private static TouchAction touchAction;

    @Test(priority = 0)
    public void initialLandingPageText() throws InterruptedException {
        try {
            touchAction = new TouchAction<>(driver);

            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            startCta.click();
        } catch (Exception e) {
            fail("landing page" + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void loginWithCrtOTP() {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
        signOut();
    }

    @Test(priority = 3)
    public void loginWithWrongOTP() {
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
            mobileInput.sendKeys(LoginPage.getMOBILE_NUMBER(3));
            proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            otpContainer = OtpPage.getOtpContainer(driver);
            assertNotEquals(otpContainer, null);
            otpLabel = OtpPage.getOtpLabel(driver);
//            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
//            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            otpEnterInput = OtpPage.getOtpEnterInput(driver);
//
//            String expectedOtpLabelText = "Enter OTP sent via SMS";
//            String expectedMobileNumberLabelText = "+91-" + LoginPage.MOBILE_NUMBER;
//            String expectedMobileNumberEditCtaText = "Edit";
//
//            assertTrue(expectedOtpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
//            assertTrue(expectedMobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
//            assertTrue(expectedMobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));

            otpEnterInput.click();
            // enter otp
            Process process = Runtime.getRuntime().exec("adb shell input text " + OtpPage.INVALID_OTP);
            process.waitFor();
            WebElement otpToastMessage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/snackbar_text"));
            System.out.println(otpToastMessage.getText());
            assertEquals("Invalid Otp Message", OtpPage.INVALID_OTP_MESSAGE, otpToastMessage.getText());
            System.out.println("OTP typed successfully." + OtpPage.INVALID_OTP);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    //    @Test
    public void copyOtp() {
        String decodedOtp = null;
        WebElement clearNotification = null;
        WebElement actionContainer = null;
        WebElement copyAction = null;
        try {
            driver.openNotifications();
            clearNotification = driver.findElement(AppiumBy.id("com.android.systemui:id/clear_all_port"));
            clearNotification.click();
        } catch (Exception e) {
            TouchAction touchAction = new TouchAction(driver);
            int xCoordinate = 627;
            int yCoordinate = 1608;
            touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
            System.out.println("no new notification");
        }
        loginWithCrtOTP();
        try {
            driver.openNotifications();
            actionContainer = driver.findElement(AppiumBy.id("android:id/actions_container_layout"));
            assertNotNull(actionContainer);
            copyAction = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Copy Verification Code\"]"));
            copyAction.click();
            System.out.println("copied otp");
            String otp = driver.getClipboard(ClipboardContentType.PLAINTEXT);
            System.out.println(otp);
            byte[] decodedBytes = Base64.getDecoder().decode(otp);
            String decodedText = new String(decodedBytes);
            System.out.println(decodedOtp);
            try {
                clearNotification = driver.findElement(AppiumBy.id("com.android.systemui:id/clear_all_port"));
                clearNotification.click();
            } catch (Exception e) {
                int xCoordinate = 627;
                int yCoordinate = 1608;
                touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
                System.out.println("no new notification");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
        signOut();
    }

    @Test(priority = 4)
    public void otpMaxLimitCheck() {
        openMobileNumberEntryScreen();
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
            mobileInput.sendKeys(LoginPage.getMOBILE_NUMBER(2));
            proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            otpContainer = OtpPage.getOtpContainer(driver);
            assertNotEquals(otpContainer, null);
            otpLabel = OtpPage.getOtpLabel(driver);
            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            otpEnterInput = OtpPage.getOtpEnterInput(driver);
            String otpLabelText = "Enter OTP sent via SMS";
            String mobileNumberLabelText = "+91-" + LoginPage.getMOBILE_NUMBER(2);
            String mobileNumberEditCtaText = "Edit";

            assertTrue(otpLabelText.trim().equalsIgnoreCase(otpLabel.getText().trim()));
            assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
            assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));

            for (int i = 1; i <= OtpPage.OTP_MAX_LIMIT; i++) {
                otpEnterInput.click();
                Process process = Runtime.getRuntime().exec("adb shell input text " + OtpPage.INVALID_OTP);
                process.waitFor();
                WebElement otpToastMessage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/snackbar_text"));
                String otpToastText = otpToastMessage.getText();
                System.out.println("OTP typed successfully." + OtpPage.INVALID_OTP);
                System.out.println(otpToastText);

                if (i == (OtpPage.OTP_MAX_LIMIT)) {
                    assertEquals("Invalid Otp Message", OtpPage.OTP_MAX_LIMIT_MESSAGE, otpToastText);
                } else {
                    assertEquals("Invalid Otp Message", OtpPage.INVALID_OTP_MESSAGE, otpToastText);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
//        signOut();
    }

    private void openMobileNumberEntryScreen() {
        try {
            Thread.sleep(2000);
            OtpPage.getMobileNumberEditCta(driver).click();
        }catch (Exception e)
        {
            fail("failed to go to edit page"+ e.getMessage());
        }
    }

    @Test(priority = 5)
    public void resentOtp() {
        openMobileNumberEntryScreen();
        WebElement mobileInput = null;
        WebElement proceedBtn = null;
        WebElement otpContainer = null;
        WebElement otpLabel = null;
        WebElement mobileNumberLabel = null;
        WebElement mobileNumberEditCta = null;
        WebElement otpEnterInput = null;
        WebElement resendOtp = null;

        try {
            mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull(mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(LoginPage.getMOBILE_NUMBER(0));
            proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            otpContainer = OtpPage.getOtpContainer(driver);
            assertNotEquals(otpContainer, null);
            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            otpEnterInput = OtpPage.getOtpEnterInput(driver);
            assertNotNull(otpEnterInput);
            String mobileNumberLabelText = "+91-" + LoginPage.getMOBILE_NUMBER(0);
            String mobileNumberEditCtaText = "Edit";
            int i = 1;
            do {
                try {
                    Thread.sleep(30000);
                    otpLabel = OtpPage.getOtpLabel(driver);
                    System.out.println(OtpPage.getINVALID_OTP_MESSAGE().trim());
                    System.out.println(otpLabel.getText().trim());
//                    assertTrue(OtpPage.getOTP_NOT_RECEIVED_LABEL_TEXT().trim().equalsIgnoreCase(otpLabel.getText().trim()));
//                    assertTrue(mobileNumberLabelText.trim().equalsIgnoreCase(mobileNumberLabel.getText().trim()));
//                    assertTrue(mobileNumberEditCtaText.trim().equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resendOtp = OtpPage.getOtpTimer(driver);
                resendOtp.click();
                System.out.println("Resend otp click times -> " + i);
                if (i == OtpPage.OTP_MAX_SENT_COUNT) {
                    try {
                        assertNotNull(OtpPage.getOtpTitleTemplate(driver));
                    } catch (Exception e) {
                        fail("Toast message not received");
                    }
                }
                i++; // Increment i
            } while (i <= OtpPage.OTP_MAX_SENT_COUNT);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test(priority = 0)
    public void openTermsAndPolicy() {
        int xCoordinate = 355;
        int yCoordinate = 565;
        WebElement privacyTitle = null;
        WebElement termsTitle = null;
        touchAction = new TouchAction<>(driver);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        touchAction.tap(PointOption.point(LoginPage.PRIVACY_X_COORDINATE, LoginPage.PRIVACY_Y_COORDINATE)).perform();
        try {
            privacyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals(LoginPage.getPRIVACY_POLICY_TITLE(), privacyTitle.getText());
            touchAction.tap(PointOption.point(544, 211)).perform();
            touchAction.tap(PointOption.point(LoginPage.TERMS_X_COORDINATE, LoginPage.TERMS_Y_COORDINATE)).perform();
            termsTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals(LoginPage.getTNC_TITLE(), termsTitle.getText());
            touchAction.tap(PointOption.point(544, 211)).perform();
        } catch (Exception e) {
            fail("something not found");
        }
    }

    private void checkPopUp() {
        WebElement popUpClose = null;
        try {
            popUp = new PopUp(driver);
            popUpClose = PopUp.getPopUpClose(driver);
            popUpClose.click();
            assertTrue("popup successfully closed", true);
        } catch (Exception e) {
            System.out.println("no popUp");
            assertTrue("popup is not there", true);
        }
    }

    private void gotoAccountPage() {
        WebElement mobileInput = null;
        try {
            navigation = new Navigation(driver);
            mobileInput = Navigation.getProfileHomeTab(driver);
            mobileInput.click();
            assertNotNull(mobileInput);
        } catch (Exception e) {
            fail("failed to go account page");
        }
    }

    private void signOut() {
        checkPopUp();
        gotoAccountPage();
        accountPage = new AccountPage(driver);
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement signOut = null;
        WebElement mobileInput = null;
        WebElement yesCta = null;
        try {
            signOut = AccountPage.getSignOut(driver);
            signOut.click();
            yesCta = driver.findElement(AppiumBy.id("android:id/button1"));
            yesCta.click();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Element not found");
        }
    }
}
