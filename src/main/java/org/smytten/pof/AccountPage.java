package org.smytten.pof;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

    private static WebElement profileSection;
    private static WebElement profileName;
    private static WebElement editProfile;
    private static WebElement editIcon;
    private static WebElement smyttenBenefits;
    private static WebElement trialHowTo;
    private static WebElement shopHowTo;
    private static WebElement rewardHowTo;
    private static WebElement myOrders;
    private static WebElement helpSection;
    private static WebElement profileEditTab;
    private static WebElement referAndEarn;
    private static WebElement referBanner;
    private static WebElement reviewSection;
    private static WebElement surveySection;
    private static WebElement savedAddress;
    private static WebElement wishlist;
    private static WebElement smyttenLuxe;
    private static WebElement smyttenBlog;
    private static WebElement privacyPolicy;
    private static WebElement termsAndCondition;
    private static WebElement faqs;
    private static WebElement smyttenLogo;
    private static WebElement signOut;
    private final AndroidDriver driver;

    public AccountPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), AccountPage.class);
    }

    public static WebElement getProfileEditTab(AndroidDriver driver) {
        return profileEditTab = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
    }

    public static WebElement getProfileSection(AndroidDriver driver) {
        return profileSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/v_edit_profile"));
    }

    public static WebElement getProfileName(AndroidDriver driver) {
        return profileName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/name"));
    }

    public static WebElement getEditProfile(AndroidDriver driver) {
        return editProfile = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/email"));
    }

    public static WebElement getEditIcon(AndroidDriver driver) {
        return editIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_edit_profile"));
    }

    public static WebElement getSmyttenBenefits(AndroidDriver driver) {
        return smyttenBenefits = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_banefits"));
    }

    public static WebElement getTrialHowTo(AndroidDriver driver) {
        return trialHowTo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_point"));
    }

    public static WebElement getShopHowTo(AndroidDriver driver) {
        return shopHowTo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_shop_wallet"));
    }

    public static WebElement getRewardHowTo(AndroidDriver driver) {
        return rewardHowTo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_reward_cash"));
    }

    public static WebElement getMyOrders(AndroidDriver driver) {
        return myOrders = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_header_1"));
    }

    public static WebElement getHelpSection(AndroidDriver driver) {
        return helpSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_header_2"));
    }

    public static WebElement getReferAndEarn(AndroidDriver driver) {
        return referAndEarn = driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Refer and Earn\"));")));
    }

    public static WebElement getReferBanner(AndroidDriver driver) {
        return referBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner"));
    }

    public static WebElement getReviewSection(AndroidDriver driver) {
        return reviewSection = driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Leave a review\"));")));
    }

    public static WebElement getSurveySection(AndroidDriver driver) {
        return surveySection = driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Take a survey\"));")));
    }

    public static WebElement getSavedAddress(AndroidDriver driver) {
        return savedAddress = driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"My Saved Address\"));")));
    }

    public static WebElement getWishlist(AndroidDriver driver) {
        return wishlist = driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"My Wishlist\"));")));
    }

    public static WebElement getSmyttenLuxe(AndroidDriver driver) {
        return smyttenLuxe = driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Smytten Luxe\"));")));
    }

    public static WebElement getSmyttenBlog(AndroidDriver driver) {
        return smyttenBlog = driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Smytten Blog\"));")));
    }

    public static WebElement getPrivacyPolicy(AndroidDriver driver) {
        return privacyPolicy = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_footer_1"));
    }

    public static WebElement getTermsAndCondition(AndroidDriver driver) {
        return termsAndCondition = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_footer_2"));
    }

    public static WebElement getFaqs(AndroidDriver driver) {
        return faqs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_footer_3"));
    }

    public static WebElement getSmyttenLogo(AndroidDriver driver) {
        return smyttenLogo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_smytten_logo"));
    }

    public static WebElement getSignOut(AndroidDriver driver) {
        return signOut = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_sign_out"));
    }
}
