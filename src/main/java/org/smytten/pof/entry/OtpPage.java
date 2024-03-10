package org.smytten.pof.entry;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class OtpPage {
    public static final String INVALID_OTP_MESSAGE = "OTP does not match. Please enter the correct OTP.";
    public static final int OTP_MAX_SENT_COUNT = 6;
    public static final int OTP_MAX_LIMIT = 11;
    public static final String OTP_MAX_LIMIT_MESSAGE = "You have already made maximum number of attempt to verify OTP. Please try again after some time.";
    public static final String INVALID_OTP = "1111";

    public static final String VALID_OTP = "1234";

    public static String getINVALID_OTP_MESSAGE() {
        return INVALID_OTP_MESSAGE;
    }

    public int getOTP_MAX_SENT_COUNT() {
        return OTP_MAX_SENT_COUNT;
    }

    public int getOTP_MAX_LIMIT() {
        return OTP_MAX_LIMIT;
    }

    public static String getINVALID_OTP() {
        return INVALID_OTP;
    }

    public static String getVALID_OTP() {
        return VALID_OTP;
    }

    public static String getMOBILE_NUMBER_EDIT_CTA_TEXT() {
        return MOBILE_NUMBER_EDIT_CTA_TEXT;
    }

    public static String getOTP_NOT_RECEIVED_LABEL_TEXT() {
        return OTP_NOT_RECEIVED_LABEL_TEXT;
    }

    public static String getOTP_RESEND_TEXT() {
        return OTP_RESEND_TEXT;
    }

    public static String getCONTACT_US_EMAIL_LABEL_TEXT() {
        return CONTACT_US_EMAIL_LABEL_TEXT;
    }
    

    public static WebElement getOtpContainer(AndroidDriver driver) {
        return otpContainer = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/cv_container"));
    }

    public static WebElement getOtpLabel(AndroidDriver driver) {
        return otpLabel = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/tv_otp_label"));
    }

    public static WebElement getMobileNumberLabel(AndroidDriver driver) {
        return mobileNumberLabel = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/tv_mobile"));
    }

    public static WebElement getMobileNumberEditCta(AndroidDriver driver) {
        return mobileNumberEditCta = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/tv_mobile_ed"));
    }

    public static WebElement getOtpText(AndroidDriver driver) {
        return otpText = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/tv_otp"));
    }

    public static WebElement getOtpTimer(AndroidDriver driver) {
        return otpTimer = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/tv_otp_timer"));
    }

    public static WebElement getEmailButton(AndroidDriver driver) {
        return emailButton = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/btn_email"));
    }

    public static WebElement getSnackbarText(AndroidDriver driver) {
        return snackbarText = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/snackbar_text"));
    }

    public static final String MOBILE_NUMBER_EDIT_CTA_TEXT = "Edit";
    public static final String OTP_NOT_RECEIVED_LABEL_TEXT = "Didn’t receive the OTP?";
    public static final String OTP_RESEND_TEXT = "Resend";
    public static final String CONTACT_US_EMAIL_LABEL_TEXT = "Having trouble signing in? Write to us! -> ";
    private final AndroidDriver driver;

    public OtpPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), OtpPage.class);
    }

    
    private static WebElement otpContainer;


    private static WebElement otpLabel;


    private static WebElement mobileNumberLabel;


    private static WebElement mobileNumberEditCta;


    private static WebElement otpText;


    private static WebElement otpTimer;


    private static WebElement otpTitleTemplate;

    private static WebElement emailButton;

    public static WebElement getOtpTitleTemplate(AndroidDriver driver) {
        return otpTitleTemplate = driver.findElement(AppiumBy.id( "com.app.smytten.debug:id/title_template"));
    }

    public static String getOTP_MAX_LIMIT_MESSAGE() {
        return OTP_MAX_LIMIT_MESSAGE;
    }

    public static WebElement getOtpEnterInput(AndroidDriver driver) {
        return otpEnterInput = driver.findElement(AppiumBy.className("android.widget.EditText"));
    }


    private static WebElement snackbarText;


    private static WebElement otpEnterInput;
}
