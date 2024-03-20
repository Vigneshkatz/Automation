package signin;

import base.BaseTest;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.util.Utility;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class SignUpTest extends BaseTest {

    @BeforeClass
    public void signUpTestSetup(){
        try {
            smyttenHelper.openLoginPage();
        }catch (Exception e){
            fail("signUpSetUp failed : "+e.getMessage());
        }
    }

    @Test(priority = 0)
    public void testSignUp() {
        try {
            // Enter mobile number and proceed to OTP
            WebElement mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull("Mobile number input field not found", mobileInput);
            androidHelper.clearAndSetValueInField(mobileInput, Utility.getNumber());

            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();
            smyttenHelper.signUpHelper();

        } catch (AssertionError | Exception e) {
            fail("signUp" + e.getMessage());
        }
    }
}
