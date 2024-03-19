package org.smytten.util.driver;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.smytten.util.payment.PaymentHelper;

public class DriverHelper {
    public   AndroidDriver driver = null;

    public DriverHelper(AndroidDriver driver){
        this.driver = driver;
    }
    public void back(){
        driver.navigate().back();
    }
    public void scrollToTop(){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(1)"));

    }
    public void scrollToBottom(){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
    }

    public void enterValue(WebElement inputBox, String value){
        inputBox.click();
        inputBox.clear();
        inputBox.sendKeys(PaymentHelper.VISA_NUMBER);
    }
}
