package org.smytten.pof.cart;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    public static WebElement getToolbar(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_toolbar"));
    }

    public static WebElement getWishlistButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_toolbar_wishlist"));
    }

    public static WebElement getWalletCashback(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/bg_wallet"));
    }

    public static WebElement getWalletTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_title"));
    }

    public static WebElement getWalletInfo(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_info"));
    }

    public static WebElement getAnimationCoupon(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_animation"));
    }

    public static WebElement getCoupon(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_coupon_code"));
    }

    public static WebElement getCouponRemove(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_coupon_code_remove"));
    }

    public static WebElement getViewAll(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_offer_head_view"));
    }

    public static WebElement getTrialTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_section_title"));
    }

    public static WebElement getTrialItemCount(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_section_count"));
    }

    public static WebElement getDeliveryBy(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_section_edd"));
    }

    public static WebElement getDeliveryByDate(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_section_date"));
    }

    public static WebElement getTrialSection(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_trial_section"));
    }

    public static WebElement getRootTrialSection(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
    }

    public static WebElement getProductTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_cart_title"));
    }

    public static WebElement getBrand(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_cart_brand"));
    }

    public static WebElement getProductRemove(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_option"));
    }

    public static WebElement getProductImage(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_cart_image"));
    }

    public static WebElement getPopupTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }



    public static WebElement getClose(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
    }



    public static WebElement getDescription(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_desc"));
    }

    public static WebElement getProductImageInPopUp(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_product"));
    }

    public static WebElement getTouchOutside(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/touch_outside"));
    }

    public static WebElement getPaymentAmount(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_total_payable_rs"));
    }

    public static WebElement getProceedButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_proceed"));
    }



    public static WebElement getConsentPopupTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_header"));
    }
}
