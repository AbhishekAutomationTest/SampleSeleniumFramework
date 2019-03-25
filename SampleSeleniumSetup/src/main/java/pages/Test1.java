package pages;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Test1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		String driverPath = new File(".").getCanonicalPath()+"/jars/chromedriver.exe";
		
		System.out.println(driverPath);
		
		System.setProperty("webdriver.chrome.driver", driverPath);
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://formy-project.herokuapp.com/dropdown");
		
		Thread.sleep(2000);
		
		WebElement dropdown = driver.findElement(By.id("dropdownMenuButton"));
		
		dropdown.click();
		
			Thread.sleep(2000);
			Actions actions = new Actions(driver);
			driver.findElement(By.linkText("Enabled and disabled elements")).click();
			
			Thread.sleep(2000);
			//actions.contextClick();
			
			System.out.println(driver.findElement(By.xpath("//*[contains(text(),'Enabled and Disabled elements')]")).isDisplayed());
			
		//WebElement image = driver.findElement(By.id("image"));
		//WebElement box = driver.findElement(By.id("box"));
		//System.out.println(driver.getClass().toString());
		
		//To test WindowsHandles
		Set<String> set = driver.getWindowHandles();
		
		Iterator<String> itr= set.iterator();
		String mainWindow="";
		
		while(itr.hasNext()){
			
			mainWindow = itr.next().toString();
			System.out.println(mainWindow);		
		}
		
		//To test switchTo()
		
		driver.switchTo().window(mainWindow);
		System.out.println("SwitchTO() Succeed");
		
		
		
		//Actions actions = new Actions(driver);
		
		//actions.dragAndDrop(image, box).build().perform();
		
		//String originalHandle = driver.getWindowHandle();
		
		//for(String handle1:driver.getWindowHandles())
		//{
			//driver.switchTo().window(handle1);
		//}
		//driver.switchTo().window(originalHandle);
		
		
		
		System.out.println("Test Complete");
		
		//driver.quit();
	}

}
