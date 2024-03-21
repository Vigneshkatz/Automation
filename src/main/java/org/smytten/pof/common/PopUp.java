package org.smytten.pof.common;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PopUp {
    private AndroidDriver driver;

    public PopUp(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), Navigation.class);
    }

    public static WebElement getCartConsentPopup(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
    }

    public static WebElement getRightCtaConsentPopUp(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_cta_right"));
    }

    public static WebElement getLeftCtaPopUp(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_cta_left"));
    }

    private static WebElement popUpClose;
    public static WebElement getPopUpClose(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/iv_close"));
    }

    public static WebElement getCodPopUp(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/cl_header"));
    }
    public static WebElement getCodProceedBtn(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/btn_left"));
    }

    public static WebElement getOfferPopUp(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/iv_image"));
    }

    public static WebElement getSignUpPopup(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/cv_dialog"));
    }

    public static WebElement getFreebieFrenzyPopUp(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/iv_bg"));
    }

    public static WebElement getFreebieFrenzyPopUpAnimation(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/iv_animation"));
    }

    public static WebElement getFreebieFrenzyPopUpHeader(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/iv_bg_head_h"));
    }
}
