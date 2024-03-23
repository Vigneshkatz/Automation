package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.WebElement;
import org.smytten.naviation.AccountPageNavigation;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.account.ProfileUpdatePage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LandingPage;
import org.smytten.pof.entry.LoginPage;
import org.smytten.util.Utility;
import org.smytten.util.helper.AndroidHelper;
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
          AccountPageNavigation.openAddressPage(driver);
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
                assertEquals("sorry, you have no saved address.", noAddressWarning.getText().toLowerCase());

            } else {
                addNewAddressCta.click();
            }
        } catch (AssertionError | Exception e) {
            fail("verifyAddressPage" + "Address page elements not found. " + e.getMessage());
        }finally {
            androidHelper.back();
        }
    }

    @Test(priority = 2)
    public void openEditProfile() {
        try {
            AccountPageNavigation.openEditProfile(driver);
        } catch (AssertionError | Exception e) {
            fail("openEditProfile assertion failed: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void updateProfile() {
        try {
            smyttenHelper.updateUserProfile();
        } catch (AssertionError | Exception e) {
            fail("updateProfile assertion failed: " + e.getMessage());
        }
    }
}
