//package org.demo;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.UiAutomator2Options;
//import io.appium.java_client.service.local.AppiumDriverLocalService;
//import io.appium.java_client.service.local.AppiumServiceBuilder;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import java.io.File;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.rmi.server.UID;
//
//public class Demo {
//    @Test
//    public void app() throws MalformedURLException, InterruptedException {
//        AppiumDriverLocalService serviceBuilder = new AppiumServiceBuilder().
//                withAppiumJS(new File("//usr//local//lib//node_modules//appium//lib//main.js"))
//                .withIPAddress("http://0.0.0.0:1").usingPort(4723).build();
//        serviceBuilder.start();
//
//        serviceBuilder.stop();
//
//
//
//        System.out.printf("Service started");
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("Pixel_3a");
//        options.setApp("/Users/Vignesh/Desktop/Automation/src/resources/ApiDemos-debug.apk");
//        Assert.assertTrue(true);
//        try {
//            AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);
//            androidDriver.getBatteryInfo();
//            androidDriver.quit();
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
