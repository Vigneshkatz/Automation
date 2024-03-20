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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class AccountPageTest extends BaseTest {
    private final Boolean isEmpty = true;

    @BeforeClass
    public void accountPageSetup(){
        try {
            smyttenHelper.openLoginPage();
            smyttenHelper.signUp(true,true);
            smyttenHelper.checkPopUp();
            smyttenHelper.gotoAccountPage();
        }catch (Exception e){
            fail("accountPageSetup :"+e.getMessage());
        }

    }

    @Test(priority = 0)
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

    @Test(priority = 1)
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

    @Test(priority = 2)
    public void openEditProfile() {
        try {
            WebElement editIcon = AccountPage.getEditIcon(driver);
            editIcon.click();

        } catch (AssertionError | Exception e) {
            fail("openEditProfile assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void updateProfile() {
        try {
            androidHelper.scrollToTop();
            smyttenHelper.updateName();
            smyttenHelper.updateEmail();
            driver.hideKeyboard();
            smyttenHelper.updateGender();
            smyttenHelper.updateDOB();
            smyttenHelper.updatePincode();
            WebElement saveBtn = ProfileUpdatePage.getProceedButton(driver);
            saveBtn.click();
        } catch (AssertionError | Exception e) {
            fail("updateProfile assertion failed: " + e.getMessage());
        }
    }
}
