package com.shopbot.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Files;
import java.nio.file.Path;

public final class ExtentReportManager {
    private static ExtentReports extentReports;

    private ExtentReportManager() {
    }

    public static synchronized ExtentReports getReport() {
        if (extentReports == null) {
            try {
                Files.createDirectories(Path.of("reports"));
            } catch (Exception exception) {
                throw new RuntimeException("Unable to create reports directory", exception);
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ShopBotExtentReport.html");
            sparkReporter.config().setDocumentTitle("ShopBot Test Report");
            sparkReporter.config().setReportName("SauceDemo Automation Results");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Application", "SauceDemo");
            extentReports.setSystemInfo("Framework", "Selenium Java TestNG");
        }
        return extentReports;
    }

    public static synchronized void flushReport() {
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
