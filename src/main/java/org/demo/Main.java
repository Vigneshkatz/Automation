package org.demo;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "realme RMX1971");
        caps.setCapability("platformName", "Android");
        caps.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/resources/ApiDemos-debug.apk");

        try {
            AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);            Thread.sleep(100000);
            driver.quit();
        } catch (MalformedURLException e) {
            System.out.println("Invalid Appium Server URL: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
