package org.demo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

public class Main {
    public static AndroidDriver driver = null;
    private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static void main(String[] args) {
        grantPermission();
        startAppiumSession();
    }

    private static void grantPermission() {
        try {
            Process process = Runtime.getRuntime().exec("adb shell pm grant com.app.smytten.debug android.permission.WRITE_SECURE_SETTINGS");
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("WRITE_SECURE_SETTINGS permission granted successfully.");
            } else {
                System.err.println("Failed to grant WRITE_SECURE_SETTINGS permission.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startAppiumSession() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "OnePlus LE2111");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("autoGrantPermissions", "true");
        caps.setCapability("enforceAppInstall", "true");
        caps.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-debug (1).apk");

        try {
            System.out.println("Starting Appium session...");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
            Thread.sleep(5000);
            login();
            Thread.sleep(10000);
            System.out.println("Automation completed successfully.");
        } catch (Exception e) {
            System.out.println("Invalid Appium Server URL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private static void login() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile")).click();
        Thread.sleep(2000);
        driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile")).sendKeys("9500752205");
        Thread.sleep(1000);
        driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed")).click();
    }
}