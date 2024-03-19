package org.smytten.pof.payment;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class CardPaymentPage {


    public static WebElement cardNumber(AndroidDriver driver){
        return driver.findElement(AppiumBy.id("card_number"));
    }
    public static WebElement cardExpiry(AndroidDriver driver){
        return  driver.findElement(AppiumBy.id("card_expiry"));
    }
    public static WebElement cardCvv(AndroidDriver driver){
        return  driver.findElement(AppiumBy.id("card_cvv"));
    }
}
