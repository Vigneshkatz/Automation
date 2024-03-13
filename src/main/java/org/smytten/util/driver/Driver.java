package org.smytten.util.driver;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class Driver {
    private  AndroidDriver driver = null;

    public Driver(AndroidDriver driver){
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
}
