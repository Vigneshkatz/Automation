package org.smytten.pof.entry;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    public static final String GROUP_INVITE_CODE = "CHcHXt";

    public AndroidDriver driver;

    public SignUpPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), SignUpPage.class);
    }


    private static WebElement WebInput;


    private static WebElement proceedBtn;


    private static WebElement maleGenderOption;


    private static WebElement femaleGenderOption;


    private static WebElement monthSpinner;


    private static WebElement marchMonthOption;


    private static WebElement yearSpinner;


    private static WebElement year2009Option;


    private static WebElement referralInput;


    private static WebElement referralApplyBtn;


    private static WebElement referralSuccessTitle;


    private static WebElement referralSuccessPaymentTitle;

    private static WebElement confirmBtn;

    public static WebElement getWebInput(AndroidDriver driver) {
        return WebInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_Web"));
    }

    public static WebElement getProceedBtn(AndroidDriver driver) {
        return proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
    }

    public static WebElement getMaleGenderOption(AndroidDriver driver) {
        return maleGenderOption = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_male"));
    }

    public static WebElement getFemaleGenderOption(AndroidDriver driver) {
        return femaleGenderOption = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_female"));
    }

    public static WebElement getMonthSpinner(AndroidDriver driver) {
        return monthSpinner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/month_spinner"));
    }

    public static WebElement getMarchMonthOption(AndroidDriver driver) {
        return marchMonthOption = driver.findElement(AppiumBy.xpath( "//android.widget.TextView[@resource-id='android:id/title' and @text='March']"));
    }

    public static WebElement getYearSpinner(AndroidDriver driver) {
        return yearSpinner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/year_spinner"));
    }

    public static WebElement getYear2009Option(AndroidDriver driver) {
        return year2009Option = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='2009']"));
    }

    public static WebElement getReferralInput(AndroidDriver driver) {
        return referralInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_referral"));
    }

    public static WebElement getReferralApplyBtn(AndroidDriver driver) {
        return referralApplyBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_referral_apply"));
    }

    public static WebElement getReferralSuccessTitle(AndroidDriver driver) {
        return referralSuccessTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_refer_success"));
    }

    public static WebElement getReferralSuccessPaymentTitle(AndroidDriver driver) {
        return referralSuccessPaymentTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_payment_title"));
    }

    public static WebElement getConfirmBtn(AndroidDriver driver) {
        return confirmBtn =  driver.findElement(AppiumBy.id("com.app.smytten.debug:id/signup_manual"));
    }

   
}
