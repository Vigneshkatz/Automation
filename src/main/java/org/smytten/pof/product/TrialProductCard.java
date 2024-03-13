package org.smytten.pof.product;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.By.id;

public class TrialProductCard {

    private static WebElement productCard;
    private static WebElement tryNowButton;
    private static WebElement productImage;
    private static WebElement productBrand;
    private static WebElement productTitle;
    private static WebElement productWorth;
    private static WebElement trialPoints;
    private static WebElement trialPointLabel;

    public static WebElement getProductCard(AndroidDriver driver) {
        return productCard = driver.findElement((AppiumBy.id(("com.app.smytten.debug:id/include_product_card"))));
    }

    public static WebElement getTryNowButton(AndroidDriver driver) {
        return tryNowButton = driver.findElement(AppiumBy.id(("com.app.smytten.debug:id/btn_try_now")));
    }

    public static WebElement getProductImage(AndroidDriver driver) {
        return productImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_product_image"));
    }

    public static WebElement getProductBrand(AndroidDriver driver) {
        return productBrand = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_product_brand"));
    }

    public static WebElement getProductTitle(AndroidDriver driver) {
        return productTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_product_title"));
    }

    public static WebElement getProductWorth(AndroidDriver driver) {
        return productWorth = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_worth"));
    }

    public static WebElement getTrialPoints(AndroidDriver driver) {
        return trialPoints = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_points"));
    }

    public static WebElement getTrialPointLabel(AndroidDriver driver) {
        return trialPointLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_point_label"));
    }

    public static List<WebElement> getAllProductCard(AndroidDriver driver) {
        return  driver.findElements((AppiumBy.id(("com.app.smytten.debug:id/include_product_card"))));
    }

    public static WebElement getPopUpDesc(AndroidDriver driver) {
        return driver.findElement((AppiumBy.id(("com.app.smytten.debug:id/wv_desc"))));
    }

    public static WebElement getPopUpOkButton(AndroidDriver driver) {
        return driver.findElement((AppiumBy.id(("com.app.smytten.debug:id/okButton"))));
    }

    public static WebElement getPopUpNoButton(AndroidDriver driver) {
        return driver.findElement((AppiumBy.id(("com.app.smytten.debug:id/cancelButton"))));
    }


}


