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

    public static WebElement getConsentPopup(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
    }

    public static WebElement getRightCtaConsentPopUp(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_cta_right"));
    }

    public static WebElement getLeftCtaPopUp(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_cta_left"));
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



}
