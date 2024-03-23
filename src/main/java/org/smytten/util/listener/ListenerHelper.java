package org.smytten.util.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import org.smytten.util.FileUtil;
import org.smytten.util.export.ExtentReportHelper;
import org.smytten.util.helper.SmyttenHelper;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerHelper implements ITestListener {
    AndroidDriver driver;

    ExtentTest extentTest;
    ExtentReports extentReports = ExtentReportHelper.getReports();

    @Override
    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, result.getThrowable());

        try {
            driver = (AndroidDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            extentTest.addScreenCaptureFromPath(FileUtil.getScreenShopPath(driver, result.getMethod().getMethodName()), result.getTestName());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        this.onTestFailure(result);
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

}