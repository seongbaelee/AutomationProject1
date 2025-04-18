package com.automation.selenium_automation.listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.automation.selenium_automation.base.Base;
import com.automation.selenium_automation.utils.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends Base implements ITestListener {
	ExtentTest test;
	ExtentReports extent = Utils.getReportObject();
	
	@Override
    public void onTestStart(ITestResult result) {
//        System.out.println("Test Started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
//        System.out.println("Test Passed: " + result.getName());
    	test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
//        System.out.println("Test Failed: " + result.getName());
    	test.fail(result.getThrowable());
    	
    	try {
    		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
    				.get(result.getInstance());
    	} catch (Exception e1) {
    		e1.printStackTrace();
    	}
    	
    	String filePath = null;
    	
		try {
			filePath = Utils.getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    	//screenshot
    }

    @Override
    public void onTestSkipped(ITestResult result) {
//        System.out.println("Test Skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        System.out.println("Test Failed but within success %: " + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
//        System.out.println("Test Failed due to timeout: " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
//        System.out.println("Test Suite Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
//        System.out.println("Test Suite Finished: " + context.getName());
    	extent.flush();
    }
}
