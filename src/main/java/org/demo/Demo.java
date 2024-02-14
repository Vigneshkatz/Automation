package org.demo;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Demo {

    public static void main(String[] args) {
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Pixel_3a_API_34_extension_level_7_x86_64");
        caps.setCapability("browserName", "Chrome");

        try {
            AndroidDriver driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
            driver.get("https://www.google.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
}