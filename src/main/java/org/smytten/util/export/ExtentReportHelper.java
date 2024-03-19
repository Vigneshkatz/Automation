package org.smytten.util.export;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.smytten.util.Utility;

public class ExtentReportHelper {

    public static  ExtentReports extentReports;

    static String path = "/Users/Vignesh/Desktop/Automation/report/index"+ Utility.getCurrentDateTime() +".html";
    public static ExtentReports getReports(){
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Smytten");
        reporter.config().setDocumentTitle("Smytten Automation");
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("User","Vignesh");
        return extentReports;
    }
}
