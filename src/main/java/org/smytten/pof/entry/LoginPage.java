package org.smytten.pof.entry;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;
import java.util.List;

public class LoginPage {
    public static final String VALID_OTP = "1234";
    // Page Elements
    private static WebElement mobileInputContainer;
    private static WebElement mobileNumberLabel;
    private static WebElement mobilePrefix;
    private static WebElement mobileNumberInput;
    private static WebElement sendOtpButton;
    private static WebElement termsAndConditionsLink;
    private static WebElement emailBasedLoginMessage;
    private static WebElement checkBox;
    private static WebElement warningTitle;
    private static WebElement toastMessage;
    private static WebElement okButton;
    private static WebElement tncPrivacyPopupTitle;
    private static WebElement tncContent;
    private static WebElement closeButton;
    // Constants
    public static final List<String> NUMBER_LIST = Arrays.asList("5111111111", "5118181818", "5122222222", "5133333333", "5144444444", "5155555555", "5166666666", "5112121212", "5114141414", "5119191919", "5115151515", "5120202020", "5116161616", "5117171717");

    public static final int PRIVACY_X_COORDINATE = 811;
    public static final int PRIVACY_Y_COORDINATE = 1999;
    public static final int TERMS_X_COORDINATE = 516;
    public static final int TERMS_Y_COORDINATE = 2049;
    public static final String MAX_OTP_EXHAUSTED = "You have already made maximum number of attempts to login. Please try to login after some time.";
    public static final String TNC_TITLE = "Terms of Service";
    public static final String PRIVACY_POLICY_TITLE = "Smytten's Privacy Policy";
    public static final String MOBILE_ENTER_LABEL_TEXT = "Please enter Mobile Number";
    private final AndroidDriver driver;

    // Constructor to initialize the page elements
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), LoginPage.class);
    }

    public static WebElement getMobileInputContainer(AndroidDriver driver) {
        return mobileInputContainer = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_mobile_cotainer"));
    }

    public static WebElement getMobileNumberLabel(AndroidDriver driver) {
        return mobileNumberLabel = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/label"));
    }

    public static WebElement getMobilePrefix(AndroidDriver driver) {
        return mobilePrefix = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"+91\"]"));
    }

    public static WebElement getMobileNumberInput(AndroidDriver driver) {
        return mobileNumberInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
    }

    public static WebElement getSendOtpButton(AndroidDriver driver) {
        return sendOtpButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
    }

    public static WebElement getTermsAndConditionsLink(AndroidDriver driver) {
        return termsAndConditionsLink = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_policy"));
    }

    public static WebElement getEmailBasedLoginMessage(AndroidDriver driver) {
        return emailBasedLoginMessage = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"E-mail based log in is no longer available.\nPlease click here to add your mobile number.\"]"));
    }

    public static WebElement getCheckBox(AndroidDriver driver) {
        return checkBox = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cb_check"));
    }

    public static WebElement getWarningTitle(AndroidDriver driver) {
        return warningTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/title_template"));
    }

    public static WebElement getToastMessage(AndroidDriver driver) {
        return toastMessage = driver.findElement(AppiumBy.id("android:id/message"));
    }

    public static WebElement getOkButton(AndroidDriver driver) {
        return okButton = driver.findElement(AppiumBy.id("android:id/button1"));
    }

    public static WebElement getTncPrivacyPopupTitle(AndroidDriver driver) {
        return tncPrivacyPopupTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
    }

    public static WebElement getTncContent(AndroidDriver driver) {
        return tncContent = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/wv_data"));
    }

    public static WebElement getCloseButton(AndroidDriver driver) {
        return closeButton = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
    }

    public static String getMOBILE_NUMBER(int i) {
        return NUMBER_LIST.get(i);
    }

    public int getPRIVACY_X_COORDINATE() {
        return PRIVACY_X_COORDINATE;
    }

    public int getPRIVACY_Y_COORDINATE() {
        return PRIVACY_Y_COORDINATE;
    }

    public int getTERMS_X_COORDINATE() {
        return TERMS_X_COORDINATE;
    }

    public int getTERMS_Y_COORDINATE() {
        return TERMS_Y_COORDINATE;
    }

    public String getMAX_OTP_EXHAUSTED() {
        return MAX_OTP_EXHAUSTED;
    }

    public static String getTNC_TITLE() {
        return TNC_TITLE;
    }

    public static String getPRIVACY_POLICY_TITLE() {
        return PRIVACY_POLICY_TITLE;
    }

    public String getMOBILE_ENTER_LABEL_TEXT() {
        return MOBILE_ENTER_LABEL_TEXT;
    }

    public AndroidDriver getDriver(AndroidDriver driver) {
        return driver;
    }
}
