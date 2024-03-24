package org.smytten.pof.rewards;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RewardProductCard {
    public static List<WebElement> getAllRProductCard(AndroidDriver driver){
        return driver.findElements(AppiumBy.id("com.app.smytten.debug:id/root_layout"));
    }

    public static WebElement getRProductAddToCartCta(WebElement driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_add"));
    }

    public static WebElement getRProductImage(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_trial"));
    }

    public static WebElement getRProductBrandName(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_company"));
    }

    public static WebElement getRProductName(WebElement driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }

    public static WebElement getRProductBucksIcon(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_bucks"));
    }

    public static WebElement getRProductTotalBucks(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_bucks"));
    }

    public static WebElement getRProductBucksLabel(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_bucks_label"));
    }

}
