package com.shopbot.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.shopbot.utils.ExtentReportManager;
import com.shopbot.utils.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> EXTENT_TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        EXTENT_TEST.set(ExtentReportManager.getReport().createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        EXTENT_TEST.get().log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String screenshotPath = ScreenshotUtil.capture(result.getMethod().getMethodName());
        EXTENT_TEST.get().log(Status.FAIL, result.getThrowable());
        EXTENT_TEST.get().addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        EXTENT_TEST.get().log(Status.SKIP, "Test skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReport();
    }
}
