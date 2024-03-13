package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.account.ProfileUpdatePage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

import static org.testng.AssertJUnit.*;

public class AccountPageTest extends BaseTest {

    private static final int RANDOMNUMBER = ThreadLocalRandom.current().nextInt(0, 2);
    private static TouchAction touchAction;


    @Test(priority = 0)
    public void initialLandingPageText() {
        try {
            touchAction = new TouchAction<>(driver);
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            startCta.click();
            recordResult("initialLandingPageText", "Pass");

        } catch (AssertionError e) {
            recordResult("initialLandingPageText", "Fail " + e.getMessage());
            fail("initialLandingPageText assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("initialLandingPageText", "Fail " + e.getMessage());
            fail("initialLandingPageText failed: " + e.getMessage());
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
            chooseGender = (RANDOMNUMBER == 0) ? maleElement : femaleElement;
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
            recordResult("signUp", "Pass");

        } catch (AssertionError e) {
            recordResult("signUp", "Fail " + e.getMessage());
            fail("signUp assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signUp", "Fail " + e.getMessage());
            fail("signUp failed: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void checkPopUp() {
        WebElement popUpClose = null;
        if(VerifyElementHelper.isPopupPresent(driver)){
            popUpClose = PopUp.getPopUpClose(driver);
            popUpClose.click();
            assertTrue("popup successfully closed", true);
            recordResult("checkPopUp", "Pass");

        }
        recordResult("CheckPopUp", "Pass");
    }

    @Test(priority = 3)
    public void gotoAccountPage() {
        WebElement mobileInput = null;
        try {
            mobileInput = Navigation.getProfileHomeTab(driver);
            mobileInput.click();
            assertTrue(true);
            recordResult("gotoAccountPage", "Pass");

        } catch (AssertionError e) {
            recordResult("gotoAccountPage", "Fail " + e.getMessage());
            fail("gotoAccountPage assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("gotoAccountPage", "Fail " + e.getMessage());
            fail("signOut failed: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void verifyAccountSection() {
        WebElement profileSection = null;
        WebElement profileName = null;
        WebElement editProfile = null;
        WebElement editIcon = null;
        WebElement smyttenBenefits = null;
        WebElement trialHowTo = null;
        WebElement shopHowTo = null;
        WebElement rewardHowTo = null;
        WebElement myOrders = null;
        WebElement helpSection = null;
        WebElement referAndEarn = null;
        WebElement referBanner = null;
        WebElement reviewSection = null;
        WebElement surveySection = null;
        WebElement savedAddress = null;
        WebElement wishlist = null;
        WebElement smyttenLuxe = null;
        WebElement smyttenBlog = null;
        WebElement privacyPolicy = null;
        WebElement termsAndCondition = null;
        WebElement faqs = null;
        WebElement smyttenLogo = null;
        WebElement signOut = null;
        WebElement yesCta = null;
        try {
            profileSection = AccountPage.getEditProfile(driver);
            profileName = AccountPage.getProfileName(driver);
            editProfile = AccountPage.getEditProfile(driver);
            editIcon = AccountPage.getEditIcon(driver);
            smyttenBenefits = AccountPage.getSmyttenBenefits(driver);
            trialHowTo = AccountPage.getTrialHowTo(driver);
            shopHowTo = AccountPage.getShopHowTo(driver);
            rewardHowTo = AccountPage.getRewardHowTo(driver);
            myOrders = AccountPage.getMyOrders(driver);
            helpSection = AccountPage.getHelpSection(driver);
            referAndEarn = AccountPage.getReferAndEarn(driver);
            referBanner = AccountPage.getReferBanner(driver);
            reviewSection = AccountPage.getReviewSection(driver);
            surveySection = AccountPage.getSurveySection(driver);
            driverHelper.scrollToBottom();
            savedAddress = AccountPage.getSavedAddress(driver);
            wishlist = AccountPage.getWishlist(driver);
            smyttenLuxe = AccountPage.getSmyttenLuxe(driver);
            smyttenBlog = AccountPage.getSmyttenBlog(driver);
            privacyPolicy = AccountPage.getPrivacyPolicy(driver);
            termsAndCondition = AccountPage.getTermsAndCondition(driver);
            faqs = AccountPage.getFaqs(driver);
            smyttenLogo = AccountPage.getSmyttenLogo(driver);
            signOut = AccountPage.getSignOut(driver);
            yesCta = driver.findElement(AppiumBy.id("android:id/button1"));
            yesCta.click();
            recordResult("verifyLandingPage", "Pass");
        } catch (AssertionError e) {
            recordResult("verifyAccountSection", "Fail " + e.getMessage());
            fail("verifyAccountSection assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signOut", "Fail " + e.getMessage());
            fail("verifyAccountSection failed: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void openEditProfile() {
        WebElement editIcon = null;

        try {
            editIcon = AccountPage.getEditIcon(driver);
            editIcon.click();
            recordResult("openEditProfile", "Pass");

        } catch (AssertionError e) {
            recordResult("openEditProfile", "Fail " + e.getMessage());
            fail("openEditProfile assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openEditProfile", "Fail " + e.getMessage());
            fail("openEditProfile failed: " + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void updateProfile() {
        WebElement editIcon = null;
        WebElement saveBtn = null;
        try {
            driverHelper.scrollToTop();
            updateName();
            updateEmail();
            driver.hideKeyboard();
            updateGender();
            updateDOB();
            updatePincode();
            saveBtn = ProfileUpdatePage.getProceedButton(driver);
            saveBtn.click();
            recordResult("updateProfile", "Pass");
        } catch (AssertionError e) {
            recordResult("updateProfile", "Fail " + e.getMessage());
            fail("updateProfile assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("updateProfile", "Fail " + e.getMessage());
            fail("updateProfile failed: " + e.getMessage());
        }
    }

    private void updatePincode() {
        WebElement pincodeInput = null;
        try {
            pincodeInput = ProfileUpdatePage.getPincodeInput(driver);
            pincodeInput.click();
            pincodeInput.sendKeys("635115");
            recordResult("updatePincode", "Pass");

        } catch (AssertionError e) {
            recordResult("updatePincode", "Fail " + e.getMessage());
            fail("updatePincode assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("updatePincode", "Fail " + e.getMessage());
            fail("updatePincode failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }

    }

    private void updateDOB() {
        WebElement chooseMonth = null;
        WebElement selectMonth = null;
        WebElement chooseYear = null;
        WebElement selectYear = null;
        try {
            chooseMonth = ProfileUpdatePage.getBirthdateInput(driver);
            chooseMonth.click();
            selectMonth = ProfileUpdatePage.getSelectMarch(driver);
            selectMonth.click();

            chooseYear = ProfileUpdatePage.getChooseYear(driver);
            chooseYear.click();

            selectYear = ProfileUpdatePage.getSelectYear(driver);
            selectYear.click();
            recordResult("updateDOB", "Pass");

        } catch (AssertionError e) {
            recordResult("updateDOB", "Fail " + e.getMessage());
            fail("updateDOB assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("updateDOB", "Fail " + e.getMessage());
            fail("updateDOB failed: " + e.getMessage());
        }
    }

    private void updateGender() {
        WebElement otherGender = null;
        try {
            otherGender = ProfileUpdatePage.getOthersOption(driver);
            otherGender.click();
            recordResult("updateGender", "Pass");

        } catch (AssertionError e) {
            recordResult("updateGender", "Fail " + e.getMessage());
            fail("updateGender assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("updateGender", "Fail " + e.getMessage());
            fail("updateGender failed: " + e.getMessage());
        }

    }

    private void updateEmail() {
        String email = Utility.generateRandomEmail();
        WebElement emailInput = null;
        try {
            emailInput = ProfileUpdatePage.getEmailInput(driver);
            emailInput.click();
            emailInput.sendKeys(email);
            recordResult("updateEmail", "Pass");

        } catch (AssertionError e) {
            recordResult("updateEmail", "Fail " + e.getMessage());
            fail("updateEmail assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("updateEmail", "Fail " + e.getMessage());
            fail("updateEmail failed: " + e.getMessage());
        }

    }

    private void updateName() {
        String name = "Not Guest User";
        WebElement nameInput = null;
        try {
            nameInput = ProfileUpdatePage.getNameInput(driver);
            nameInput.click();
            nameInput.sendKeys(name);
            recordResult("updateName", "Pass");
        } catch (AssertionError e) {
            recordResult("updateName", "Fail " + e.getMessage());
            fail("updateName assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("updateName", "Fail " + e.getMessage());
            fail("updateName failed: " + e.getMessage());
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
        signUp();

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
            recordResult("copyOtp", "Pass");

        } catch (AssertionError e) {
            recordResult("copyOtp", "Fail " + e.getMessage());
            fail("copyOtp assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("copyOtp", "Fail " + e.getMessage());
            fail("copyOtp failed: " + e.getMessage());
        }
    }
}
