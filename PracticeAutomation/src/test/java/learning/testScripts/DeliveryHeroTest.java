package learning.testScripts;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import learning.utils.SSCapture;

public class DeliveryHeroTest extends BaseTest {
	
	WebDriver driver;
	static String imageDir;
	ExtentTest extentTest;
	

	@Parameters({"URL2","Browser-Name"})
	@BeforeTest()
	public void init(String url, String browserName, final ITestContext testContext) throws IOException 
	{
		
		driver = setUp(url, browserName, testContext);
		System.out.println(testContext.getName());
		
	}//beforeClass Method
	
	
	@Test(priority =1)
	public void enterDetails() throws IOException 
	{
		extentTest = extentReports.createTest("enterDetails");
		key.setExtentTest(extentTest);
		System.out.println("Inside Test Method 1 with Extent Reports");
		WebElement logoImage = key.returnElement("xpath", "//a[@href='https://www.deliveryhero.com']");
		assertTrue(key.isDisplayed(logoImage, "logoImage"));
	}
	
	@Test(dependsOnMethods="enterDetails")
	public void searchHotelwithMinimumPrice() throws IOException
	{
		extentTest = extentReports.createTest("searchHotelwithMinimumPrice");
		key.setExtentTest(extentTest);
		System.out.println("Inside Test Method 2");
		WebElement logoImage = key.returnElement("xpath", "//*[@data-selenium='textInput']]");
		assertTrue(key.isDisplayed(logoImage, "logoImage"));
	}
	
	@Test(dependsOnMethods="searchHotelwithMinimumPrice")
	public void confirmBooking() throws IOException
	{
		extentTest= extentReports.createTest("confirmBooking");
		key.setExtentTest(extentTest);
		System.out.println("Inside Test Method 2");
		WebElement logoImage = key.returnElement("xpath", "//*[@data-selenium='textInput']]");
		assertTrue(key.isDisplayed(logoImage, "logoImage"));
	}
	
	@AfterTest
	public void closeBrowser() {
		tearDown(driver);
	}
		
}