package learning.PracticeAutomation;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * Hello world!
 *
 */


public class App 
{
	public static void main( String[] args ) throws IOException
    {
        System.out.println( "Hello World!" );
    
    System.setProperty("webdriver.chrome.driver", new File(".").getCanonicalPath()+"/jars/chromedriver.exe");
        WebDriver driver= new ChromeDriver();
    
    driver.get("http://extentreports.com/docs/versions/3/java/");
    
        driver.quit();
    
    }
    
    
    
}
