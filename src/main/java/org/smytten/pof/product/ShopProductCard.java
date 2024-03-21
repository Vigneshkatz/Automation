package org.smytten.pof.product;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShopProductCard {

    public static WebElement getProductCardContainer(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/container"));
    }

    public static List<WebElement> getAllProductCard(AndroidDriver driver) {
        return driver.findElements(AppiumBy.id("com.app.smytten.debug:id/container"));
    }

    public static WebElement getProductCardLabel(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_label"));

    }

    public static WebElement getProductCardImage(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_trial_grid"));
    }

    public static WebElement getProductCardBrandName(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_brand_name"));
    }

    public static WebElement getProductCardProductName(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_product_name"));
    }

    public static WebElement getProductCardMrp(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_product_grid_mrp"));
    }

    public static WebElement getProductCardPrice(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_price"));
    }

    public static WebElement getProductCardFavIcon(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_fav"));
    }

    public static WebElement getProductCardAddToCart(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_try_now"));
    }
    public static void addProductToCard(WebElement productCard) {
        productCard.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_try_now")).click();
    }

}
