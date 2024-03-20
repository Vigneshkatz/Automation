package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.account.ProfileUpdatePage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.util.Utility;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class AccountPageTest extends BaseTest {
    private final Boolean isEmpty = true;

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
            androidHelper.clearAndSetValueInField(mobileInput, Utility.getNumber());
            WebElement proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();
            smyttenHelper.signUpHelper();
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
            androidHelper.scrollToTop();
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
            androidHelper.clearAndSetValueInField(pincodeInput, "635115");
        } catch (AssertionError | Exception e) {
            fail("updatePincode assertion failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }

    }

    private void updateDOB() {
        try {
            WebElement chooseMonth = ProfileUpdatePage.getBirthdateInput(driver);
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
            androidHelper.clearAndSetValueInField(emailInput, email);
        } catch (AssertionError | Exception e) {
            fail("updateEmail assertion failed: " + e.getMessage());
        }
    }

    private void updateName() {
        String name = "Not Guest User";
        try {
            WebElement nameInput = ProfileUpdatePage.getNameInput(driver);
            androidHelper.clearAndSetValueInField(nameInput, name);
        } catch (AssertionError | Exception e) {
            fail("updateName assertion failed: " + e.getMessage());
        }
    }
}
