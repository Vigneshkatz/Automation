package org.smytten.naviation;

import io.appium.java_client.android.AndroidDriver;
import org.smytten.pof.common.Navigation;

public class SmyttenNavigation {

    public static void goToTrailFront(AndroidDriver driver) throws Exception {
        Navigation.getTrialHomeTab(driver).click();
    }

    public static void goToShopFront(AndroidDriver driver) throws Exception {
        Navigation.getShopHomeTab(driver).click();
    }

    public static void goToRewardFront(AndroidDriver driver) throws Exception {
        Navigation.getRewardHomeTab(driver).click();
    }

    public static void goToAccountPage(AndroidDriver driver) throws Exception {
        Navigation.getHamburgerIcon(driver).click();
    }
}
