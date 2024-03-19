package org.smytten.pof.product;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class ShopFrontPage {

    public static WebElement getWalletPopup(AndroidDriver driver) {
       return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_bg_banner"));
    }

    public static WebElement getWalletPopupArrowDown(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_arrow_down"));
    }


}
