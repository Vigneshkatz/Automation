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
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.smytten.pof.entry.LandingPage;
import org.testng.Assert;
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
            recordResult("initialLandingPageText", "Pass");

        }catch (AssertionError e) {
            recordResult("initialLandingPageText", "Fail "+e.getMessage());
            Assert.fail("initialLandingPageText assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signUp", "Fail "+e.getMessage());
            Assert.fail("initialLandingPageText failed: " + e.getMessage());
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
            recordResult("loginWithCrtOTP", "Pass");

        } catch (AssertionError e) {
            recordResult("loginWithCrtOTP", "Fail "+e.getMessage());
            Assert.fail("loginWithCrtOTP assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signUp", "Fail "+e.getMessage());
            Assert.fail("loginWithCrtOTP failed: " + e.getMessage());
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
            recordResult("loginWithWrongOTP", "Pass");

        } catch (AssertionError e) {
            recordResult("loginWithWrongOTP", "Fail "+e.getMessage());
            Assert.fail("loginWithWrongOTP assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("loginWithWrongOTP", "Fail "+e.getMessage());
            Assert.fail("loginWithWrongOTP failed: " + e.getMessage());
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
            recordResult("otpMaxLimitCheck", "Pass");


        }catch (AssertionError e) {
            recordResult("otpMaxLimitCheck", "Fail "+e.getMessage());
            Assert.fail("otpMaxLimitCheck assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("otpMaxLimitCheck", "Fail "+e.getMessage());
            Assert.fail("otpMaxLimitCheck failed: " + e.getMessage());
        }
//        signOut();
    }

    private void openMobileNumberEntryScreen() {
        try {
            Thread.sleep(2000);
            OtpPage.getMobileNumberEditCta(driver).click();
            recordResult("openMobileNumberEntryScreen", "Pass");

        }catch (AssertionError e) {
            recordResult("openMobileNumberEntryScreen", "Fail "+e.getMessage());
            Assert.fail("openMobileNumberEntryScreen assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openMobileNumberEntryScreen", "Fail "+e.getMessage());
            Assert.fail("openMobileNumberEntryScreen failed: " + e.getMessage());
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
                i++;
            } while (i <= OtpPage.OTP_MAX_SENT_COUNT);
            recordResult("resentOtp", "Pass");

        } catch (AssertionError e) {
            recordResult("resentOtp", "Fail "+e.getMessage());
            Assert.fail("resentOtp assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("resentOtp", "Fail "+e.getMessage());
            Assert.fail("resentOtp failed: " + e.getMessage());
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
            recordResult("openTermsAndPolicy", "Pass");

        }catch (AssertionError e) {
            recordResult("openTermsAndPolicy", "Fail "+e.getMessage());
            Assert.fail("openTermsAndPolicy assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openTermsAndPolicy", "Fail "+e.getMessage());
            Assert.fail("openTermsAndPolicy failed: " + e.getMessage());
        }
    }

    private void checkPopUp() {
        WebElement popUpClose = null;
        if(VerifyElementHelper.isPopupPresent(driver)){
            popUpClose = PopUp.getPopUpClose(driver);
            popUpClose.click();
            assertTrue("popup successfully closed", true);
            recordResult("checkPopUp", "Pass");

        }
        recordResult("CheckPopUp", "Pass");
    }

    private void gotoAccountPage() {
        WebElement mobileInput = null;
        try {
            navigation = new Navigation(driver);
            mobileInput = Navigation.getProfileHomeTab(driver);
            mobileInput.click();
            assertNotNull(mobileInput);
            recordResult("SignUp", "Pass");
        } catch (AssertionError e) {
            recordResult("signUp", "Fail "+e.getMessage());
            Assert.fail("singUp assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signUp", "Fail "+e.getMessage());
            Assert.fail("signup failed: " + e.getMessage());
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
            recordResult("signOut", "Pass");
        } catch (AssertionError e) {
            recordResult("signOut", "Fail "+e.getMessage());
            Assert.fail("signOut assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signOut", "Fail "+e.getMessage());
            Assert.fail("signOut failed: " + e.getMessage());
        }
    }
}
