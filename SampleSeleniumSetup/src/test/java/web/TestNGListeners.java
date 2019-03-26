package web;

import org.testng.IClass;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestNGListeners implements ITestListener
{

	@Override
	public void onTestStart(ITestResult tr) {
		log("Test Started....");
	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
		
		log("Test '" + tr.getName() + "' PASSED");
		log(tr.getTestClass());
		log("Priority of this method is " + tr.getMethod().getPriority());
		System.out.println(".....");
		
	}
	
	@Override
	public void onTestFailure(ITestResult tr) {
		
		log("Test '" + tr.getName() + "' FAILED");
		log("Priority of this method is " + tr.getMethod().getPriority());
		System.out.println(".....");
	}
	
	@Override
	public void onTestSkipped(ITestResult tr) {
		log("Test '" + tr.getName() + "' SKIPPED");
		System.out.println(".....");
	}
	
	
	public void log(String methodName) {
		System.out.println(methodName);
	}
	
	public void log(IClass testclass) {
		System.out.println(testclass);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
}
