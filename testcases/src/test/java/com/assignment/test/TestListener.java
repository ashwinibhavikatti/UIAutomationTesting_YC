package com.assignment.test;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener extends TestBase implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().toString().trim();
		String TestName = result.getTestContext().getName();
		captureScreenshot(TestName,methodName,"Fail");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("***** Success " + result.getName() + " test has passed *****");
		String methodName = result.getName().toString().trim();
		String TestName = result.getTestContext().getName();
		captureScreenshot(TestName,methodName,"Pass");
	}
}
