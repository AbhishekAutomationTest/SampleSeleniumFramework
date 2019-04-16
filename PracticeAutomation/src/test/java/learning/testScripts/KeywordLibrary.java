package learning.testScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import learning.utils.SSCapture;

public class KeywordLibrary {

	WebDriver driver;
	WebDriverWait wait;
	ExtentTest extentTest;
	String SSImageDir;
	static int stepCount=1;
	
	public String getSSImageDir() {
		return SSImageDir;
	}

	public void setSSImageDir(String sSImageDir) {
		SSImageDir = sSImageDir;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
		setWait(new WebDriverWait(getDriver(), 5));
	}

	public WebDriverWait getWait() {
		return wait;
	}

	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void setExtentTest(ExtentTest extentTest) {
		this.extentTest = extentTest;
		
	}
	
	public ExtentTest getExtentTest() {
		return extentTest;
	}
	
	public boolean isDisplayed(WebElement element, String elementName) throws IOException 
	
	{
		String[] elementAddr = element.toString().split(">");
		System.out.println("Looking for " + elementName);
		
		
		if(element.isDisplayed())
		 {	 
			System.out.println(elementName+" found."); 
			getExtentTest().log(Status.PASS, elementName+" with "+ elementAddr[1] +" is displayed",MediaEntityBuilder.createScreenCaptureFromPath(SSCapture.captureSS(driver,getSSImageDir()+"Passed/Step"+stepCount++)).build());
			 return true;	
		 }
		 else
		 {
			 System.out.println(elementName+" Not found.");
			 
			 getExtentTest().log(Status.FAIL, elementName+" is not present",MediaEntityBuilder.createScreenCaptureFromPath(SSCapture.captureSS(driver,getSSImageDir()+"Failed/Step"+stepCount++)).build());
			 return false;
		 }
	}
	
	public WebElement returnElement(String locatorName, String locator) throws IOException {
		
		WebElement element=null;
		
		try {
			if (locatorName.equals("xpath"))
				{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
				element = getDriver().findElement(By.xpath(locator));
				getExtentTest().log(Status.PASS,"Located the element with "+locatorName+ " = "+locator);
				}
			else if (locatorName.equals("id"))
				{
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
				element = getDriver().findElement(By.id(locator));
				getExtentTest().log(Status.PASS,"Located the element with "+locatorName+ " = "+locator);
				}
			else 
				{
				System.out.println("Please provide xpath or id of element to be located");
				getExtentTest().log(Status.FAIL,"Please provide xpath or id of element to be located");
				}
			return element;
			
			}
			
		catch(TimeoutException e)
		{
			System.out.println("Could not locate the element with "+locatorName+ " = "+locator );
			System.out.println(e);
			getExtentTest().log(Status.FAIL, "Could not locate the element with "+locatorName+ " = "+locator, MediaEntityBuilder.createScreenCaptureFromPath(SSCapture.captureSS(driver,getSSImageDir()+"Failed/Step"+stepCount++)).build());
			return element;
		}	
		
		catch(NoSuchElementException e)
		{
			System.out.println("No such element present with "+locatorName+ " = "+locator );
			System.out.println(e);
			getExtentTest().log(Status.FAIL, "Could not find the element with "+locatorName+ " = "+locator, MediaEntityBuilder.createScreenCaptureFromPath(SSCapture.captureSS(driver,getSSImageDir()+"Failed/Step"+stepCount++)).build());
			return element;
		}	
		
		}
		
	}
	

