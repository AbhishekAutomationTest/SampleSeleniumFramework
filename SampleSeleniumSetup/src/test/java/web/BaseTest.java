package web;

import java.io.File;
import java.net.MalformedURLException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

	public WebDriver driver = null;
	public WebDriverWait wait = null;
	
	
	//Take valid Browser Name from TestNG and open browser.

	public void driverSetup(String browser) throws Exception 
	{	
			if(browser.equalsIgnoreCase("chrome"))
		{	
				String driverPath = new File(".").getCanonicalPath()+"/jars/chromedriver.exe";
				System.out.println(driverPath);
				System.setProperty("webdriver.chrome.driver", driverPath);
				driver = new ChromeDriver();	
		}
			else if (browser.equalsIgnoreCase("firefox"))
		{
				String driverPath = new File(".").getCanonicalPath()+"/jars/geckodriver.exe";
				System.out.println(driverPath);
				System.setProperty("webdriver.firefox.driver", driverPath);
				driver = new FirefoxDriver();
		}
			else if (browser.equalsIgnoreCase("edge") || browser.equalsIgnoreCase("Microsoft edge") )
		{
				String driverPath = new File(".").getCanonicalPath()+"/jars/MicrosoftWebDriver.exe";
				System.out.println(driverPath);
				System.setProperty("webdriver.edge.driver", driverPath);
				driver = new EdgeDriver();
		}
			else
		{
				System.out.println("Please specify valid Browser Name");
		}
			
	}
	
	public void navigateToURL(String url) throws MalformedURLException
		{
			driver.get(url);
			driver.manage().window().maximize();
		}
	
	public WebElement returnElement(String selector, String locator)
		{
			wait = new WebDriverWait(driver, 10);
		
			if(selector.equalsIgnoreCase("xpath"))
			{
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			}
			else if(selector.equalsIgnoreCase("id"))
			{
			return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
			}
			
				return null;
			}
			
	public void tearDown() 
		{
		driver.quit();
		}
	
}
