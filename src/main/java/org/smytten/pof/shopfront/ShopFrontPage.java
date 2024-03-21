package org.smytten.pof.shopfront;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopFrontPage {
    public static List<WebElement> getMenuList(AndroidDriver driver) {
        return driver.findElements(AppiumBy.id("com.app.smytten.debug:id/iv_circle_view"));
    }
    public static WebElement getWalletPopup(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_bg_banner"));
    }

    public static WebElement getWalletPopupArrowDown(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_arrow_down"));
    }

}
