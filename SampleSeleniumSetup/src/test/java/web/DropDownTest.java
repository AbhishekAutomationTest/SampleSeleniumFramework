package web;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DropDownTest {

	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod
	public void setUp() throws IOException 
	{
		System.setProperty("webdriver.chrome.driver", new File(".").getCanonicalPath()+"/jars/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000,TimeUnit.MILLISECONDS);
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		
		wait = new WebDriverWait(driver, 5);
		driver.get("http://formy-project.herokuapp.com/dropdown");
		
	}
	
	@Test
	public void TestDropdown()
	{
		
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='dropdown-menu']")));
		//driver.findElement(By.xpath("//div[@class='dropdown-menu']")).click();
		WebElement dropDownMenu = driver.findElement(By.xpath("//div[@class='dropdown-menu']"));
		
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='dropdown-menu show']")));
		Select select = new Select(dropDownMenu);
		
		select.selectByVisibleText("Modal");
		
	}
	
}
