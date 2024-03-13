package org.smytten.pof.cart;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class TrialOrderConfirmation {

    public static final String ORDER_CONFIRMATION_TITLE = "Order Confirmed";
    public static final String THANKS_TEXT = "Thank You For Placing Your Order!";
    public static final String ORDER_DETAIL = "View Order Details";
    public static WebElement getTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }

    public static WebElement getThankYouText(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_thank_you_f"));
    }

    public static WebElement getViewOrderDetailButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_keep_exploring"));
    }

    public static WebElement getTopBanner(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
    }

    public static WebElement getClaimNowButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_top"));
    }

    public static WebElement getWalletAmount(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_banner_amt"));
    }

    public static WebElement getCreditedText(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_banner_title"));
    }

    public static WebElement getExpiry(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_valid"));
    }
    public static WebElement getReferBanner(AndroidDriver driver) {
    return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_bottom"));
    }

}
