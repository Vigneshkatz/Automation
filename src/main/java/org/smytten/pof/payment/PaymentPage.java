package org.smytten.pof.payment;


import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {
    public static WebElement getAllPaymentsOption(AndroidDriver driver) {
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"UPI/ DEBIT / CREDIT CARDS / NET BANKING / WALLETS \"));")));

    }

    public static WebElement getPaytmOption(AndroidDriver driver) {
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"PAYTM (Wallet / Cards / Net Banking)\"));")));

    }

    public static WebElement getSimpleOption(AndroidDriver driver) {
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Simpl (Buy Now, Pay Later) \"));")));

    }

    public static WebElement getUpiOption(AndroidDriver driver) {
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"UPI \"));")));

    }

    public static WebElement getCodOption(AndroidDriver driver) {
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Cash On Delivery \"));")));

    }

    public static WebElement getProceedBtn(AndroidDriver driver) {
        return driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
    }
}
