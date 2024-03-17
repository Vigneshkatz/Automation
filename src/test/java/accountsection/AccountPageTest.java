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

import static org.testng.AssertJUnit.*;

public class AccountPageTest extends BaseTest {
    private Boolean isEmpty = true;
    @Test(priority = 0)
    public void initialLandingPageText() {
        try {
            touchAction = new TouchAction<>(driver);
            WebElement startCta = LandingPage.getStartCtaElement(driver);
            assertNotNull(startCta);
            startCta.click();

        } catch (AssertionError e) {
            fail("initialLandingPageText assertion failed: " + e.getMessage());
        } catch (Exception e) {
            fail("initialLandingPageText failed: " + e.getMessage());
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
        } catch (AssertionError | Exception e) {
            fail("signUp" + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void testCheckPopUp() {
        try {
            if (VerifyElementHelper.isPopupPresent(driver)) {
                WebElement popUpClose = PopUp.getPopUpClose(driver);
                assertNotNull("Popup close button not found", popUpClose);
                popUpClose.click();
                assertTrue("Popup successfully closed", true);
            } else {
                assertTrue("No popUp present", true);
            }
        } catch (AssertionError | Exception e) {
            fail("checkPopUp " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void gotoAddressPage() {
        try {
            WebElement menu = Navigation.getProfileHomeTab(driver);
            assertNotNull("Menu element is not null", menu);

            WebElement savedAddress = AccountPage.getSavedAddress(driver);
            if (savedAddress != null) {
                savedAddress.click();
                assertTrue("Saved address tapped successfully.", true);
            } else {
                fail("No saved address found");
            }
        } catch (AssertionError | Exception e) {
            fail("gotoAddressPage" + "Saved address element not found." + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void verifyAddressPage() {
        try {
            WebElement addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            WebElement addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));

            // Assert common elements
            assertEquals("Shipping Address", addressTitle.getText());
            assertEquals("Add New Address +", addNewAddressCta.getText());

            if (isEmpty) {
                WebElement noAddressWarning = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
                WebElement placeholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));

                assertNotNull("Placeholder image is displayed", placeholderImage);
                assertEquals("Sorry, you have no saved address.", noAddressWarning.getText().toLowerCase());

                Thread.sleep(2000);
                addNewAddressCta.click();
            } else {
                Thread.sleep(1000);
                addNewAddressCta.click();
            }
        } catch (AssertionError | Exception e) {
            fail("verifyAddressPag" + "Address page elements not found. " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void openEditProfile() {
        try {
            WebElement editIcon = AccountPage.getEditIcon(driver);
            editIcon.click();

        } catch (AssertionError | Exception e) {
            fail("openEditProfile assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 6)
    public void updateProfile() {
        try {
            driverHelper.scrollToTop();
            updateName();
            updateEmail();
            driver.hideKeyboard();
            updateGender();
            updateDOB();
            updatePincode();
            WebElement saveBtn = ProfileUpdatePage.getProceedButton(driver);
            saveBtn.click();
        } catch (AssertionError | Exception e) {
            fail("updateProfile assertion failed: " + e.getMessage());
        }
    }

    private void updatePincode() {
        try {
            WebElement pincodeInput = ProfileUpdatePage.getPincodeInput(driver);
            pincodeInput.click();
            pincodeInput.sendKeys("635115");
        } catch (AssertionError | Exception e) {
            fail("updatePincode assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }

    }

    private void updateDOB() {
        try {
            WebElement chooseMonth  = ProfileUpdatePage.getBirthdateInput(driver);
            chooseMonth.click();
            WebElement selectMonth = ProfileUpdatePage.getSelectMarch(driver);
            selectMonth.click();

            WebElement chooseYear = ProfileUpdatePage.getChooseYear(driver);
            chooseYear.click();

            WebElement selectYear = ProfileUpdatePage.getSelectYear(driver);
            selectYear.click();

        } catch (AssertionError | Exception e) {
            fail("updateDOB assertion failed: " + e.getMessage());
        }
    }

    private void updateGender() {
        try {
            WebElement otherGender = ProfileUpdatePage.getOthersOption(driver);
            otherGender.click();
        } catch (AssertionError | Exception e) {
            fail("updateGender assertion failed: " + e.getMessage());
        }
    }

    private void updateEmail() {
        String email = Utility.generateRandomEmail();
        try {
            WebElement emailInput = ProfileUpdatePage.getEmailInput(driver);
            emailInput.click();
            emailInput.sendKeys(email);
        } catch (AssertionError | Exception e) {
            fail("updateEmail assertion failed: " + e.getMessage());
        }
    }

    private void updateName() {
        String name = "Not Guest User";
        try {
            WebElement nameInput = ProfileUpdatePage.getNameInput(driver);
            nameInput.click();
            nameInput.sendKeys(name);
        } catch (AssertionError | Exception e) {
            fail("updateName assertion failed: " + e.getMessage());
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
        testSignUp();
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
        } catch (AssertionError | Exception e) {
            fail("copyOtp assertion failed: " + e.getMessage());
        }
    }
}
