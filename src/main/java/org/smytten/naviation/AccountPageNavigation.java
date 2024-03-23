package org.smytten.naviation;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AccountPage;
import org.smytten.util.helper.AndroidHelper;

import static org.testng.AssertJUnit.*;

public class AccountPageNavigation {
    AndroidDriver driver;
    AndroidHelper androidHelper;
    TouchAction touchAction;

    public AccountPageNavigation(AndroidDriver driver, AndroidHelper androidHelper, TouchAction touchAction) {
        this.androidHelper = androidHelper;
        this.driver = driver;
        this.touchAction = touchAction;
    }

    public static void openMyOrderPage(AndroidDriver driver) throws AssertionError, Exception {
        WebElement myOrders = AccountPage.getMyOrders(driver);
        assertNotNull(myOrders);
        myOrders.click();
    }

    public static void openRewardsHowTo(AndroidDriver driver) {
        WebElement rewardHowTo = AccountPage.getRewardHowTo(driver);
        assertNotNull(rewardHowTo);
        rewardHowTo.click();
    }

    public static void openAddressPage(AndroidDriver driver) throws AssertionError, Exception {
        WebElement savedAddress = AccountPage.getSavedAddress(driver);
        if (savedAddress != null) {
            savedAddress.click();
            assertTrue("Saved address tapped successfully.", true);
        } else {
            fail("No saved address found");
        }
    }

    public static void openShopHowTo(AndroidDriver driver) {
        WebElement shopHowTo = AccountPage.getShopHowTo(driver);
        assertNotNull(shopHowTo);
        shopHowTo.click();
    }

    public static void openTrialHowTo(AndroidDriver driver) {
        WebElement trialHowTo = AccountPage.getTrialHowTo(driver);
        assertNotNull(trialHowTo);
        trialHowTo.click();
    }

    public static void openHelpSection(AndroidDriver driver) {
        WebElement helpSection = AccountPage.getHelpSection(driver);
        helpSection.click();
    }

    public static void openReviewSection(AndroidDriver driver) {
        WebElement reviewSection = AccountPage.getReviewSection(driver);
        reviewSection.click();
        assertNotNull("Review section is not null", reviewSection);
    }

    public static void openEditProfile(AndroidDriver driver) {
        WebElement editIcon = AccountPage.getEditIcon(driver);
        editIcon.click();
    }

    public static void openWishlist(AndroidDriver driver) {
        WebElement wishlist = AccountPage.getWishlist(driver);
        assertNotNull("Wishlist element is not null", wishlist);
        wishlist.click();
    }

    public static void openLuxeEntry(AndroidDriver driver) {
        WebElement smyttenLuxe = AccountPage.getSmyttenLuxe(driver);
        assertNotNull("Smytten Luxe element is not null", smyttenLuxe);
        smyttenLuxe.click();
    }

    public static void openTnC(AndroidDriver driver) {
        WebElement tnc = AccountPage.getTermsAndCondition(driver);
        assertNotNull("Terms and Condition element is not null", tnc);
        tnc.click();
    }

    public static void openPrivacyPolicy(AndroidDriver driver) {
        WebElement privacy = AccountPage.getPrivacyPolicy(driver);
        assertNotNull("Privacy Policy element is not null", privacy);
        privacy.click();
    }
}
