package org.smytten.pof.entry;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class LandingPage {

    private static final String EXPECTED_CTA_TEXT = "Get started";
    @AndroidFindBy(className = "//android.widget.TextView[@text='Get Started']")
    private  WebElement startCtaElement;

    @AndroidFindBy(id = "com.app.smytten.debug:id/iv_header")
    private  WebElement rootContent;
    @AndroidFindBy(id = "com.app.smytten.debug:id/iv_header")
    private  WebElement topBanner;
    private AndroidDriver driver;

    public LandingPage() {
        try {
            PageFactory.initElements(new AppiumFieldDecorator((SearchContext) DriverManager.getDriver("http://127.0.0.1:4723/")), this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static String getExpectedCtaText() {
        return EXPECTED_CTA_TEXT;
    }
}
