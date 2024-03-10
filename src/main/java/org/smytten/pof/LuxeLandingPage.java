package org.smytten.pof;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LuxeLandingPage {

    private static final String LUXE_TITLE = "Smytten Luxe";

    private AndroidDriver driver;
    
    private static WebElement luxeTopImageView;

    
    private static WebElement closeButton;

    
    private static WebElement knowMoreButton;

    
    private static WebElement proceedButton;

    
    private static WebElement summaryLayout;

    
    private static WebElement paymentModeTextView;

    
    private static WebElement proceedSummaryButton;

    
    private static WebElement luxeTitle;

    public LuxeLandingPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), LuxeLandingPage.class);
    }

    public static WebElement getLuxeTopImageView(AndroidDriver driver) {
        return luxeTopImageView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_luxe_top"));
    }

    public static WebElement getLuxeTitle(AndroidDriver driver) {
        return luxeTitle =driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_subtitle"));
    }

    public static WebElement getCloseButton(AndroidDriver driver) {
        return closeButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
    }

    public static WebElement getKnowMoreButton(AndroidDriver driver) {
        return knowMoreButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_know_more"));
    }

    public static WebElement getProceedButton(AndroidDriver driver) {
        return proceedButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
    }

    public static WebElement getSummaryLayout(AndroidDriver driver) {
        return summaryLayout = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_summary"));
    }

    public static WebElement getPaymentModeTextView(AndroidDriver driver) {
        return paymentModeTextView = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_payment_mode"));
    }

    public static WebElement getProceedSummaryButton(AndroidDriver driver) {
        return proceedSummaryButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
    }
    public String getLuxeTitleText() {
        return LUXE_TITLE;
    }
}
