package org.smytten.pof;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class RazorpayPaymentStatusPage {
    private AndroidDriver driver;

    
    private static WebElement initialContent;


    private static WebElement headerImage;


    private static WebElement titleSection;


    private static WebElement callToActionSection;


    private static WebElement successButton;


    private static WebElement failureButton;

    public static WebElement getInitialContent(AndroidDriver driver) {
        return initialContent = driver.findElement(AppiumBy.xpath("//android.webkit.WebView[@text='Razorpay Bank']"));
    }

    public static WebElement getHeaderImage(AndroidDriver driver) {
        return headerImage =  driver.findElement(AppiumBy.xpath("//android.widget.Image[@text=\"phplgbGKN\"]"));
    }

    public static WebElement getTitle(AndroidDriver driver) {
        return titleSection = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Welcome to Razorpay Software Private Ltd Bank\"]"));
    }

    public static WebElement getCallToActionSection(AndroidDriver driver) {
        return callToActionSection = driver.findElement(AppiumBy.xpath("//android.webkit.WebView[@text=\"Razorpay Bank\"]/android.view.View[2]"));
    }

    public static WebElement getSuccessButton(AndroidDriver driver) {
        return successButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Success\"]"));
    }

    public static WebElement getFailureButton(AndroidDriver driver) {
        return failureButton = driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Failure\"]"));
    }

    public RazorpayPaymentStatusPage(AndroidDriver driver) {

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), RazorpayPaymentStatusPage.class);
    }
}
