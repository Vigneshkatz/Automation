package signin;

import base.BaseTest;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.fail;

public class SignUpTest extends BaseTest {
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
    @Test(priority = 1)
    public void signUp() {
        WebElement mobileInput = null;
        WebElement proceedBtn = null;
        WebElement otpContainer = null;
        WebElement otpLabel = null;
        WebElement mobileNumberLabel = null;
        WebElement mobileNumberEditCta = null;
        WebElement otpEnterInput = null;
        WebElement chooseGender = null;

        try {
            mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull(mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(Utility.getNumber());
            proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();

//            otpContainer = OtpPage.getOtpContainer(driver);
//            assertNotEquals(otpContainer, null);
//            otpLabel = OtpPage.getOtpLabel(driver);
//            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
//            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
//            otpEnterInput = OtpPage.getOtpEnterInput(driver);
//            assertNotNull(otpLabel);
//            assertNotNull(mobileNumberLabel);
//            assertNotNull(mobileNumberEditCta);
//            assertNotNull(otpEnterInput);

            WebElement maleElement = SignUpPage.getMaleGenderOption(driver);
            WebElement femaleElement = SignUpPage.getFemaleGenderOption(driver);
            chooseGender = (Utility.RANDOMNUMBER == 0) ? maleElement : femaleElement;
            chooseGender.click();

            SignUpPage.getMonthSpinner(driver).click();
            SignUpPage.getMarchMonthOption(driver).click();

            SignUpPage.getYearSpinner(driver).click();
            SignUpPage.getYear2009Option(driver).click();

            SignUpPage.getReferralInput(driver).click();
            SignUpPage.getReferralInput(driver).sendKeys(SignUpPage.GROUP_INVITE_CODE);

            SignUpPage.getReferralApplyBtn(driver).click();

            // Wait for referral success title to be visible
            SignUpPage.getReferralSuccessTitle(driver);
            assertNotNull(SignUpPage.getReferralSuccessTitle(driver));
            System.out.println(SignUpPage.getReferralSuccessTitle(driver).getText());

            System.out.println(SignUpPage.getReferralSuccessPaymentTitle(driver).getText());

            SignUpPage.getConfirmBtn(driver).click();
        } catch (Exception e) {
            fail("Some element not found");
        }
    }

}
