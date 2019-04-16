package learning.testScripts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;

public class BaseTest {

	static ExtentReports extentReports;
	static String timeStamp;
	static String imageDir;
	static String projectDir; 
	KeywordLibrary key = new KeywordLibrary();
	WebDriver driver;
	
	public WebDriver setUp(String url, String browserName, ITestContext testContext) throws IOException {
	
		System.out.println("inside BeforeTest Method");
		projectDir = new File(".").getCanonicalPath();
		imageDir ="./reports/screenshots/"+timeStamp+"/"+browserName+"/"+testContext.getName()+"/";
		
		switch (browserName) {
		case "chrome": {
			System.setProperty("webdriver.chrome.driver", projectDir + "/jars/chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		}

		case "firefox": {
			break;
			
		}
		}// End Switch

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.MILLISECONDS);
		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
		
		key.setDriver(driver);
		key.setSSImageDir(imageDir);
		
		return driver;
	}// beforeTest Method

	public void tearDown(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeSuite
	public void instantiateExtentReport() throws IOException 
	{
		System.out.println("Before Suite from Base class");
		extentReports = new ExtentReporting().extentReporting();
		timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	}
	
	@AfterSuite
	public void reportFlush() {

		System.out.println("AfterSuite from Base class");
		if(extentReports !=null) {
			extentReports.flush();
		}
	}

}
