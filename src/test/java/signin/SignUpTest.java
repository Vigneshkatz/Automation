package signin;

import base.BaseTest;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class SignUpTest extends BaseTest {

    @Test(priority = 0)
    public void verifyStartCtaOnInitialLandingPage() {
        try {
            touchAction = new TouchAction<>(driver);
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
            androidHelper.clearAndSetValueInField(mobileInput, Utility.getNumber());

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();
            signUpHelper();

        } catch (AssertionError | Exception e) {
            fail("signUp" + e.getMessage());
        }
    }
}
