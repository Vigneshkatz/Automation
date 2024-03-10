package org.smytten.pof.luxe;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LuxeOrderConfirmation {

    private static WebElement rootLayout;
    private static WebElement headerImageView;
    private static WebElement sizeTextView;
    private static WebElement orderIdTextView;
    private static WebElement estimatedDeliveryDateTextView;
    private static WebElement totalPayableTextView;
    private static WebElement totalPayableRsTextView;
    private static WebElement membershipBannerImageView;
    private static WebElement termsAndConditionsTextView;
    private static WebElement exploreButton;
    private final AndroidDriver driver;


    public LuxeOrderConfirmation(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), LuxeOrderConfirmation.class);
    }

    public static WebElement getRootLayout(AndroidDriver driver) {
        return rootLayout = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
    }

    public static WebElement getHeaderImageView(AndroidDriver driver) {
        return headerImageView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_header"));
    }

    public static WebElement getSizeTextView(AndroidDriver driver) {
        return sizeTextView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_size"));
    }

    public static WebElement getOrderIdTextView(AndroidDriver driver) {
        return orderIdTextView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_order_id"));
    }

    public static WebElement getEstimatedDeliveryDateTextView(AndroidDriver driver) {
        return estimatedDeliveryDateTextView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_edd"));
    }

    public static WebElement getTotalPayableTextView(AndroidDriver driver) {
        return totalPayableTextView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_total_payable"));
    }

    public static WebElement getTotalPayableRsTextView(AndroidDriver driver) {
        return totalPayableRsTextView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_total_payable_rs"));
    }

    public static WebElement getMembershipBannerImageView(AndroidDriver driver) {
        return membershipBannerImageView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_membership_banner"));
    }

    public static WebElement getTermsAndConditionsTextView(AndroidDriver driver) {
        return termsAndConditionsTextView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_tnc"));
    }

    public static WebElement getExploreButton(AndroidDriver driver) {
        return exploreButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_explore"));
    }


}

