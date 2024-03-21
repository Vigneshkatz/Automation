package org.smytten.pof.common;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class Header {

    public static WebElement getWishlistIcon(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wishlist"));
    }

    public static WebElement getCartIcon(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_cart"));
    }

    public static WebElement getBackButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_back"));
    }

    public static WebElement getBrandLogo(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_brand_logo"));
    }

    public static WebElement getTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }

    public static WebElement getSubTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_sub_title"));
    }

    public static WebElement getSnackBar(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/snackbar_text"));
    }

    public static WebElement getToolbar(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_toolbar"));
    }
    public static WebElement getHeaderToolbar(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/toolbar"));
    }

    public static WebElement getToolbarIcon(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_trial_point"));
    }

    public static WebElement getToolbarTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tvToolbarTitle"));
    }

    public static WebElement getToolbarSubtitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tvToolbarSubtitle"));
    }


}
