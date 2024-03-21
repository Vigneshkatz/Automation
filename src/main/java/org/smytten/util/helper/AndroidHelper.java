package org.smytten.util.helper;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.smytten.util.payment.PaymentHelper;

import java.io.IOException;
import java.util.Base64;

public class AndroidHelper {
    public   AndroidDriver driver = null;

    public AndroidHelper(AndroidDriver driver){
        this.driver = driver;
    }
    public void back(){
        driver.navigate().back();
    }
    public void scrollToTop(){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(1)"));

    }
    public void scrollToBottom(){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
    }

    public void enterValue(WebElement inputBox, String value){
        inputBox.click();
        inputBox.clear();
        inputBox.sendKeys(PaymentHelper.VISA_NUMBER);
    }

    public String decodeOtp(String otp) {
        byte[] decodedBytes = Base64.getDecoder().decode(otp);
        return new String(decodedBytes);
    }

    public void clearNotifications() {
        try {
            driver.openNotifications();
            WebElement clearNotification = driver.findElement(AppiumBy.id("com.android.systemui:id/clear_all_port"));
            clearNotification.click();
        }catch (AssertionError | Exception e) {
            System.out.println("No new notification");
        }
    }

    public void clearAndSetValueInField(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
        driver.hideKeyboard();
    }

    public void enterValue(String value) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("adb shell input text " + value);
        process.waitFor();
        System.out.println("Value typed successfully: " + value);
    }

}
