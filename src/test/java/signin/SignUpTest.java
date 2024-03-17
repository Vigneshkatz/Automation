package signin;

import base.BaseTest;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class SignUpTest extends BaseTest {

    @Test(priority = 0)
    public void verifyStartCtaOnInitialLandingPage(){
        try {
            touchAction = new TouchAction<>(driver);
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull( "Start CTA element not found",startCta);
            startCta.click();
        }catch (AssertionError | Exception e){
            fail("verifyStartCtaOnInitialLandingPage "+ e.getMessage());
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
}
