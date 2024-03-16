package org.smytten.util;

import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;

import java.io.File;

public class FileUtil {
    public static String getScreenShopPath(AndroidDriver driver, String fileName) {
        final String SCREEN_SHOT_PATH = "/Users/Vignesh/Desktop/Automation/screenshots/";
        String destinationPath = SCREEN_SHOT_PATH + fileName +"_"+Utility.getCurrentDateTime()+ ".png";
        try {
            File file = driver.getScreenshotAs(OutputType.FILE);
            System.out.println("Screenshot saved: " + fileName);
            FileUtils.copyFile(file, new File(destinationPath));
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot: " + e.getMessage());
        }
        return destinationPath;
    }
}
