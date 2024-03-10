package org.smytten.pof.common;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class Navigation {

    private static WebElement trialHomeTab;
    private static WebElement shopHomeTab;
    private static WebElement rewardHomeTab;
    private static WebElement profileHomeTab;
    private static WebElement cartView;
    private static WebElement hamburgerIcon;
    private static WebElement searchIcon;
    private static WebElement toolbarCardView;
    private final AndroidDriver driver;

    public Navigation(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), Navigation.class);
    }


    public static WebElement getTrialHomeTab(AndroidDriver driver) {
        return trialHomeTab = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/trail_home_tab"));
    }

    public static WebElement getShopHomeTab(AndroidDriver driver) {
        return shopHomeTab = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/shop_home_tab"));
    }

    public static WebElement getRewardHomeTab(AndroidDriver driver) {
        return rewardHomeTab = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/reward_home_tab"));
    }

    public static WebElement getProfileHomeTab(AndroidDriver driver) {
        return profileHomeTab = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
    }

    public static WebElement getCartView(AndroidDriver driver) {
        return cartView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_cart"));
    }

    public static WebElement getHamburgerIcon(AndroidDriver driver) {
        return hamburgerIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_hamburger"));
    }

    public static WebElement getSearchIcon(AndroidDriver driver) {
        return searchIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_search"));
    }

    public static WebElement getToolbarCardView(AndroidDriver driver) {

        return toolbarCardView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_toolbar"));
    }
}
