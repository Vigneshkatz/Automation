package org.smytten.pof.entry;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    private AndroidDriver driver;
    private static WebElement startCtaElement;
    private static final String EXPECTED_CTA_TEXT = "Get started";

    public LandingPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), LandingPage.class);

    }

    public static WebElement getStartCtaElement(AndroidDriver driver) {
        return startCtaElement = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Get Started']"));
    }

    public static String getExpectedCtaText() {
        return EXPECTED_CTA_TEXT;
    }
}
