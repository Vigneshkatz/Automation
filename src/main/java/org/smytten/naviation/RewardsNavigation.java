package org.smytten.naviation;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.sikuli.script.App;
import org.smytten.pof.rewards.RewardFrontPage;

public class RewardsNavigation {

    public static void openRewardListing(AndroidDriver driver){
        RewardFrontPage.getRewardListingPage(driver).click();
    }
    public static void openRewardEarnPage(AndroidDriver driver){
        RewardFrontPage.getRewardEarnPage(driver).click();
    }

    public static void openRewardHistoryPage(AndroidDriver driver){
        RewardFrontPage.getRewardHistoryPage(driver).click();
    }

    public static void openRewardFrontPage(AndroidDriver driver){
        RewardFrontPage.getRewardFrontPage(driver).click();
    }


}
