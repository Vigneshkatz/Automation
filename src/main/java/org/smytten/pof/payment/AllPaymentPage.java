package org.smytten.pof.payment;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.smytten.util.contants.Bank;

public class AllPaymentPage {
    public static WebElement upiOption(AndroidDriver driver){
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"UPI ...\"));")));
    }
    public static WebElement cardOption(AndroidDriver driver){
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Card ...\"));")));
    }
    public static WebElement netBankingOption(AndroidDriver driver){
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Netbanking ...\"));")));
    }
    public static WebElement walletOption(AndroidDriver driver){
        return driver.findElement(AppiumBy.androidUIAutomator(("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Wallet ...\"));")));
    }

    public static WebElement bankOption(AndroidDriver driver, String bank){
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"" + Bank.valueOf(bank) + "\"));"));
    }

    public static WebElement proceedPaymentBtn(AndroidDriver driver){
        return  driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Pay Now\"));"));
    }
}