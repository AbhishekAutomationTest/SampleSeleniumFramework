package web;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Verify;


public class TestSeleniumEasy 
{
	static BaseTest base = new BaseTest();
	
	@BeforeMethod
	public static void initialSetup() throws Exception 
	{
		base.driverSetup("chrome");	
			
		base.navigateToURL("https://www.seleniumeasy.com/test/");
		System.out.println("Navigated to URL");
	}
	
	@Test(enabled= false)
	public void testDragANDDrop() throws Exception 
	
	{
		
	base.returnElement("xpath","//ul[@id=\"treemenu\"]//a[contains(text(),'Others')]/preceding::i[1]").click();
	System.out.println("Others Expanded");
	
	base.returnElement("xpath","//ul[@id=\"treemenu\"]//a[contains(text(),'Others')]/following::a[1]").click();
	System.out.println("Clicked on Drag&Drop Option");
	
	WebElement dragItem1= base.returnElement("xpath","//div[@id='todrag']/span[contains(text(),'1')]");
	System.out.println("Found DragItem 1");
	
	WebElement dropZone= base.returnElement("xpath","//div[@id='mydropzone']");
	System.out.println("Found Dropzone");
	
	Actions action = new Actions(base.driver);
	action.moveToElement(dragItem1).perform();
	//action.dragAndDrop(dragItem1,dropZone).build().perform();
	action.clickAndHold(dragItem1).moveToElement(dropZone).release().build().perform();
	
	System.out.println("Drag&Drop Action Performed");
	
	//base.returnElement("xpath","//div[@id='droppedlist']/span").getText();
	assertTrue(base.returnElement("xpath","//div[@id='droppedlist']/span").isDisplayed());
	
	System.out.println("Item Successfully Dragged & dropped.");
	
	}
	
	@Test(priority=1) 
	public void testCheckBox() throws Exception 
	
	{
		
	base.returnElement("xpath","//ul[@id=\"treemenu\"]//a[contains(text(),'Input Forms')]/preceding::i[1]").click();
	System.out.println("Input Forms Expanded");
		
	base.returnElement("xpath","//ul[@id=\"treemenu\"]//a[contains(text(),'Input Forms')]/following::a[contains(text(),'Checkbox')]").click();
	System.out.println("Clicked on checkbox Demo");	
		
	WebElement checkBox = base.returnElement("id","isAgeSelected");
	
	if(checkBox.isSelected()!=true)
		checkBox.click();
	else
		System.out.println("CheckBox is already checked");
	
	SoftAssert softAssertion= new SoftAssert();
	
	softAssertion.assertTrue(base.returnElement("id","txtAge").isDisplayed(),"Success message is not displayed");
	System.out.println(base.returnElement("id","txtAge").getText());
	
	}
	
	@Test(priority=0) 
	public void testInputField() throws Exception 
	
	{
		
	base.returnElement("xpath","//ul[@id=\"treemenu\"]//a[contains(text(),'Input Forms')]/preceding::i[1]").click();
	System.out.println("Input Forms Expanded");
		
	base.returnElement("xpath","//ul[@id=\"treemenu\"]//a[contains(text(),'Input Forms')]/following::a[1]").click();
	System.out.println("Clicked on Sample Forms Demo");
		
	WebElement textBox = base.returnElement("id","user-message");
	textBox.clear();
	String typeMessage = "Selenium Test EnterText";
	textBox.sendKeys(typeMessage);
	System.out.println("Text Entered");
	
	base.returnElement("xpath", "//form[@id='get-input']/button").click();
	
	String printMessage = base.returnElement("id","display").getText();
	System.out.println(printMessage);
	assertEquals(printMessage, typeMessage);
	System.out.println("Message printed Correctly");
	
	}
	
	@AfterMethod
	public void TearDown()
	{
		base.tearDown();
	}
	
}
