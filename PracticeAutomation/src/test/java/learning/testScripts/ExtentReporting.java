package learning.testScripts;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporting {

	static ExtentReports extent;
	ExtentTest logger;
	
	
	public ExtentReports extentReporting()
	{
		System.out.println("Inside ExtentReporting Class");
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./reports/PracticeAutomation.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		return extent;
	}
	
	
	public static void flushReporting()
	{
		System.out.println("Inside AfterSuite");
		extent.flush();
	}
	
	
}
