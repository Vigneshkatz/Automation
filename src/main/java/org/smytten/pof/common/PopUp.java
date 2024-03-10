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

    private static WebElement popUpClose;
    public static WebElement getPopUpClose(AndroidDriver driver) {
        return popUpClose = driver.findElement(AppiumBy.id ("com.app.smytten.debug:id/iv_close"));
    }
}
