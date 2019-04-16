package web;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Driver;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;

public class AgodaHotelBook {
	
	
	static WebDriver driver;
	static WebDriverWait wait;
	BaseTest b = new BaseTest();
	static ExtentHtmlReporter reporter;
	static ExtentReports extent;
	static ExtentTest logger;
	
  @Test
  public static void f() {
	  
	wait = new WebDriverWait(driver,5000);
	
	try {
	
	WebElement inputBox = driver.findElement(By.xpath("//div[@data-selenium='autocomplete-box']//input"));
	inputBox.click();
	inputBox.clear();
	inputBox.sendKeys("bangkok");
	
	logger.log(Status.PASS,"Clicked & entered Bangkok");
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-selenium='autocompletePanel']")));
	
	List<WebElement> autoCompleteOptions = driver.findElements(By.xpath("//div[@data-selenium='autocompletePanel']//self::ul/li[1]"));
	WebElement selection = autoCompleteOptions.get(0);
	selection.click();
	
	logger.log(Status.PASS,"Seleted Bangkok from dynamic DropDown");
	
	if(!driver.findElement(By.xpath("//div[@class='DayPicker-Caption']/div")).isDisplayed()) {
	driver.findElement(By.xpath("//div[@data-selenium='checkInText']")).click();
	}
	
	WebElement checkInDay = driver.findElement(By.xpath("//div[@data-selenium=\"checkInText\"]/following-sibling::div"));
	System.out.println(checkInDay.getText());
	
	String date = "22 June 2019";
	selectDate(driver, date);
	logger.log(Status.PASS, "Entered CheckInDate as "+ date);
	
	
	date = "25 June 2019";
	selectDate(driver, date);
	
	logger.log(Status.PASS, "Entered CheckOut Date as "+ date);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-selenium='occupancyBox']//span[@data-selenium='adultValue']")));
	WebElement occupancyBox = driver.findElement(By.xpath("//div[@data-selenium='occupancyBox']//span[@data-selenium='adultValue']"));
	occupancyBox.click();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-selenium='traveler-couples']")));
	WebElement roomOption = driver.findElement(By.xpath("//div[@data-selenium='traveler-couples']"));
	roomOption.click();
	
	String actualAdultValue = driver.findElement(By.xpath("//span[@data-selenium='adultValue']")).getText();
	String actualRoomValue = driver.findElement(By.xpath("//div[@data-selenium='roomValue']")).getText();
	
	SoftAssert asrt = new SoftAssert();
	asrt.assertEquals(actualAdultValue, "2 adults");
	asrt.assertEquals(actualRoomValue,"1 room");
	
	WebElement searchButton = driver.findElement(By.xpath("//button[@data-selenium='searchButton']"));
	searchButton.click();
	
	System.out.println(driver.findElement(By.xpath("//span[text()='Arnoma Grand']")).isDisplayed());
	
	asrt.assertAll();
	
	}
	catch(NoSuchElementException e) {
							
		logger.fail(e);
  }
	
  }
  @BeforeMethod
  public static void beforeMethod() throws IOException {
	 
	  String driverPath = new File(".").getCanonicalPath() + "/jars/chromedriver.exe";
	  System.setProperty("webdriver.chrome.driver", driverPath);
	  //DesiredCapabilities capabilities = new DesiredCapabilities();
	  
	 //capabilities.chrome();
	 //capabilities.setBrowserName("chrome");
	  //capabilities.setCapability(InternetExplorerDriver.
			 // INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
	 
	 	driver = new ChromeDriver();
	    driver.get("https://www.agoda.com/");
	    driver.manage().window().maximize();
	    
	  System.out.println("Browser Launched");
	  
	  reporter = new ExtentHtmlReporter("./reports/extentReportDemo.html");
	  extent = new ExtentReports();
	  
	  extent.attachReporter(reporter);
	  logger = extent.createTest("f");
	  
	  
	  
  }

  @AfterMethod
  public static void afterMethod() {
	  extent.flush();
	  driver.close();
	  
  }

  public static void selectDate (WebDriver driver,String date) {
  
	 String[] dMY= date.split(" ");

	 List<WebElement> datePickerCaption = driver.findElements(By.xpath("//div[@class='DayPicker-Caption']/div"));
	 int listSize = datePickerCaption.size();
	 int count=0;
  
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DayPicker-Caption']/div")));
  
  
  for(WebElement e: datePickerCaption) 
  {
	  count ++;
	  if(e.getText().split(" ")[0].equals(dMY[1]))
	  {
		 //WebElement monthWidget = driver.findElement(By.xpath("//div[contains(text(),'" + dMY[1]+"')]/ancestor::div[@class='DayPicker-Month']")); 
		 WebElement dayPicker = driver.findElement(By.xpath("//div[contains(text(),'"+dMY[1]+"')]/ancestor::div[@class='DayPicker-Month']//span[text()='"+dMY[0]+"']"));
		 
		 dayPicker.click();
		 WebElement Day = driver.findElement(By.xpath("//div[@data-selenium=\"checkInText\"]"));
		 System.out.println(Day.getText());
		 
		 break;
		}
	  else {
		  if (count==listSize)
		  {
			  WebElement next = driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
			  next.click();
			  selectDate(driver, date);
		  }
		  
	  }
  }
	  
  }
  }