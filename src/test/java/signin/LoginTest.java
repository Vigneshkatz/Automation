package signin;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;


public class LoginTest extends BaseTest {
   @BeforeClass
   public void loginTestSetUp(){
       try {
           smyttenHelper.openLoginPage();
       }catch (Exception e){
           fail("LoginTestSetup fail : "+e.getMessage());
       }
   }

    @Test(priority = 1)
    public void testOpenTermsAndPolicy() {
        try {
            int xCoordinate = 355;
            int yCoordinate = 565;
            // Tap on the screen to open privacy policy
            touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
            touchAction.tap(PointOption.point(LoginPage.PRIVACY_X_COORDINATE, LoginPage.PRIVACY_Y_COORDINATE)).perform();

            // Verify privacy policy title
            WebElement privacyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals(LoginPage.getPRIVACY_POLICY_TITLE(), privacyTitle.getText(), "Privacy policy title mismatch");

            // Tap on the screen to go back
            touchAction.tap(PointOption.point(544, 211)).perform();

            // Tap on the screen to open terms and conditions
            touchAction.tap(PointOption.point(LoginPage.TERMS_X_COORDINATE, LoginPage.TERMS_Y_COORDINATE)).perform();

            // Verify terms and conditions title
            WebElement termsTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals(LoginPage.getTNC_TITLE(), termsTitle.getText(), "Terms and conditions title mismatch");

            // Tap on the screen to go back
            touchAction.tap(PointOption.point(544, 211)).perform();
        } catch (AssertionError | Exception e) {
            fail("openTermsAndPolicy" + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testLoginWithCorrectOTP() {
        try {
            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile input field not found", mobileInput);
            androidHelper.clearAndSetValueInField(mobileInput, LoginPage.getMOBILE_NUMBER(1));

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            WebElement otpContainer = OtpPage.getOtpContainer(driver);
            assertNotNull("OTP container not found", otpContainer);

            WebElement otpEnterInput = OtpPage.getOtpEnterInput(driver);
            otpEnterInput.click();

            androidHelper.enterValue(OtpPage.VALID_OTP);
            System.out.println("OTP typed successfully." + OtpPage.VALID_OTP);
        } catch (AssertionError | Exception e) {
            fail("loginWithCrtOTP assertion failed: " + e.getMessage());
        }
        try {
            smyttenHelper.signOut();

        }catch (Exception e){
            fail("signout failed"+e.getMessage());
        }
    }

    @Test(priority = 3)
    public void testLoginWithWrongOTP() {
        try {
            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile input field not found", mobileInput);
            androidHelper.clearAndSetValueInField(mobileInput, LoginPage.getMOBILE_NUMBER(1));

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            WebElement otpContainer = OtpPage.getOtpContainer(driver);
            assertNotNull("OTP container not found", otpContainer);

            WebElement otpEnterInput = OtpPage.getOtpEnterInput(driver);
            otpEnterInput.click();
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
            androidHelper.enterValue(OtpPage.INVALID_OTP);
            WebElement otpToastMessage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/snackbar_text"));
            assertEquals("Invalid Otp Message", OtpPage.INVALID_OTP_MESSAGE, otpToastMessage.getText());

        } catch (AssertionError | Exception e) {
            fail("loginWithWrongOTP assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void testOtpMaxLimitCheck() {
        try {
            openMobileNumberEntryScreen();

            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile input field not found", mobileInput);
            androidHelper.clearAndSetValueInField(mobileInput, LoginPage.getMOBILE_NUMBER(2));


            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            WebElement otpContainer = OtpPage.getOtpContainer(driver);
            assertNotNull("OTP container not found", otpContainer);

            WebElement otpLabel = OtpPage.getOtpLabel(driver);
            assertNotNull("OTP label not found", otpLabel);

            WebElement mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
            assertNotNull("Mobile number label not found", mobileNumberLabel);

            WebElement mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            assertNotNull("Mobile number edit CTA not found", mobileNumberEditCta);

            WebElement otpEnterInput = OtpPage.getOtpEnterInput(driver);
            assertNotNull("OTP enter input field not found", otpEnterInput);

            String otpLabelText = "Enter OTP sent via SMS";
            String mobileNumberLabelText = "+91-" + LoginPage.getMOBILE_NUMBER(2);
            String mobileNumberEditCtaText = "Edit";

            assertEquals(otpLabelText.trim(), otpLabel.getText().trim(), "OTP label text mismatch");
            assertEquals(mobileNumberLabelText.trim(), mobileNumberLabel.getText().trim(), "Mobile number label text mismatch");
            assertEquals(mobileNumberEditCtaText.trim(), mobileNumberEditCta.getText().trim(), "Mobile number edit CTA text mismatch");

            for (int i = 1; i <= OtpPage.OTP_MAX_LIMIT; i++) {
                otpEnterInput.click();
                Process process = Runtime.getRuntime().exec("adb shell input text " + OtpPage.INVALID_OTP);
                process.waitFor();
                WebElement otpToastMessage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/snackbar_text"));
                String otpToastText = otpToastMessage.getText();
                System.out.println("OTP typed successfully: " + OtpPage.INVALID_OTP);
                System.out.println("OTP Toast message: " + otpToastText);

                String expectedMessage = (i == OtpPage.OTP_MAX_LIMIT) ? OtpPage.OTP_MAX_LIMIT_MESSAGE : OtpPage.INVALID_OTP_MESSAGE;
                assertEquals("Invalid Otp Message", expectedMessage, otpToastText);
            }
        } catch (AssertionError | Exception e) {
            fail("otpMaxLimitCheck" + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void testResendOtp() {
        try {
            openMobileNumberEntryScreen();

            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile input field not found", mobileInput);
            androidHelper.clearAndSetValueInField(mobileInput, LoginPage.getMOBILE_NUMBER(0));

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

            WebElement otpContainer = OtpPage.getOtpContainer(driver);
            assertNotNull("OTP container not found", otpContainer);

            WebElement mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
            assertNotNull("Mobile number label not found", mobileNumberLabel);

            WebElement mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            assertNotNull("Mobile number edit CTA not found", mobileNumberEditCta);

            WebElement otpEnterInput = OtpPage.getOtpEnterInput(driver);
            assertNotNull("OTP enter input field not found", otpEnterInput);

            String mobileNumberLabelText = "+91-" + LoginPage.getMOBILE_NUMBER(0);
            assertEquals(mobileNumberLabelText, mobileNumberLabel.getText(), "Mobile number label mismatch");
            assertEquals("Edit", mobileNumberEditCta.getText(), "Mobile number edit CTA mismatch");

            WebElement resendOtp;
            for (int i = 1; i <= OtpPage.OTP_MAX_SENT_COUNT; i++) {
                try {
                    Thread.sleep(30000);
                    WebElement otpLabel = OtpPage.getOtpLabel(driver);
                    System.out.println("Expected OTP message: " + OtpPage.getINVALID_OTP_MESSAGE().trim());
                    System.out.println("Received OTP message: " + otpLabel.getText().trim());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resendOtp = OtpPage.getOtpTimer(driver);
                resendOtp.click();
                System.out.println("Resend OTP click count: " + i);
                if (i == OtpPage.OTP_MAX_SENT_COUNT) {
                    try {
                        assertNotNull("Toast message not received", OtpPage.getOtpTitleTemplate(driver));
                    } catch (Exception e) {
                        fail("Toast message not received");
                    }
                }
            }
        } catch (AssertionError | Exception e) {
            fail("resentOtp " + e.getMessage());
        }
    }


    private void openMobileNumberEntryScreen() {
        try {
            Thread.sleep(2000);
            WebElement mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            assertNotNull("Mobile number edit CTA not found", mobileNumberEditCta);
            mobileNumberEditCta.click();
        } catch (AssertionError | Exception e) {
            fail("openMobileNumberEntryScreen " + e);
        }
    }
}
