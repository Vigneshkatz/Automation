package org.smytten.pof.cart;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class AddressAndPaymentPage {
    public static WebElement getToolbar(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/rl_toolbar"));
    }

    public static WebElement getTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }

    public static WebElement getAddressBar(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_address"));
    }

    public static WebElement getChangeButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_change_address"));
    }

    public static WebElement getTypeIcon(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_home_default"));
    }

    public static WebElement getAddressTitle(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_title"));
    }

    public static WebElement getAddressName(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_name"));
    }

    public static WebElement getAddressString(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_address_name"));
    }

    public static WebElement getBanner(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_row_shop_banner"));
    }

    public static WebElement getSummaryPayment(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_summary"));
    }

    public static WebElement getDetailArrow(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_detail_arrow1"));
    }

    public static WebElement getTotalPayable(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_total_payable"));
    }

    public static WebElement getPaymentHeader(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_payment_header"));
    }

    public static WebElement getDeliveryAppiumBy(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_edd_h"));
    }

    public static WebElement getPaymentList(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_payment_list"));
    }

    public static WebElement getConvenienceIcon(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_convenience"));
    }

    public static WebElement getConvenienceBand(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_convenience"));
    }

    public static WebElement getPaymentFooter(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_footer"));
    }

    public static WebElement getProceedButton(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
    }

    public static WebElement getBannerBottom(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_bottom"));
    }

    public static WebElement getPrivacyPolicy(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_policy"));
    }
}
