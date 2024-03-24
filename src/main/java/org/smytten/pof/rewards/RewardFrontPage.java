package org.smytten.pof.rewards;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class RewardFrontPage {

    public static WebElement getRewardListingPage(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_tab_3"));
    }
    public static WebElement getRewardEarnPage(AndroidDriver driver){
       return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_tab_2"));
    }

    public static WebElement getRewardHistoryPage(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_tab_4"));
    }

    public static WebElement getRewardFrontPage(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_tab_1"));
    }
}
