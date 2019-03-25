package web;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;

public class GeeksForGeeksMultipleHoverTest {

	
	static WebDriver driver=null;
	WebDriverWait wait1 = new WebDriverWait(driver, 10);
	
	
	@BeforeClass
	public static void init () throws Exception
	{
		String driverPath = new File(".").getCanonicalPath()+"/jars/chromedriver.exe";
		System.out.println(driverPath);
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		driver = new ChromeDriver();
		driver.get("https://www.geeksforgeeks.org/browser-automation-using-selenium/");
		driver.manage().window().maximize();
	}
	
	@Test
	public void test() throws Exception 
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu-item-146718\"]/a"))).build().perform();
		
		By submenu1 = By.xpath("//*[@id=\"menu-item-203861\"]/a[contains(text(),'Core Subjects')]");
		
		wait1.until(ExpectedConditions.presenceOfElementLocated(submenu1));
		
		actions.moveToElement(driver.findElement(submenu1)).build().perform();
		System.out.println("howered on core subjects");
		
		By submenu2 = By.xpath("//*[@id=\"menu-item-146724\"]/a");
		wait1.until(ExpectedConditions.presenceOfElementLocated(submenu2));
		
		actions.moveByOffset(50,0);
		
		Thread.sleep(2000);
		actions.moveToElement(driver.findElement(submenu2)).build().perform();
		Thread.sleep(2000);
		actions.click();
		
		//actions.moveToElement(driver.findElement(submenu2)).perform();
		//driver.findElement(submenu2).click();
		
		System.out.println("clicked on DBMS");
		
		System.out.println((driver.findElement(By.xpath("//*[@id=\"post-146830\"]/div/header/h1"))).isDisplayed());
		
		
		System.out.println("TestCase Passed");
	}

	@AfterClass
	public static void tearDown() {
		//driver.quit();
	}
	
}
