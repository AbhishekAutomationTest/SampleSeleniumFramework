package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Arrays;
import java.util.List;

public class DropDown {
    private static WebDriver driver = null;
    private static Actions action;

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/jars/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://www.geeksforgeeks.org/";
        //String url = "https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html";
        driver.get(url);
        List<String> dropDownXpaths = null;
        action = new Actions(driver);


        //For geeks for geeks website
        String xpath1 = "//a[text()='Quizzes ▼']";
        String xpath2 = "//ul[@class='sub-menu']//a[text()='Languages ►']";
        String xpath3 = "//ul[@class='sub-menu']//a[text()='Languages ►']/..//ul[@class='sub-menu']//a[text()='C']";

        dropDownXpaths = Arrays.asList(xpath1,xpath2,xpath3);
        clickDropDownValue(dropDownXpaths, false);

        //For Selenium easy
       /* String xpath1 = "//a[contains(text(),'Input Forms')]";
        String xpath2 = "//a[contains(text(),'Ajax Form Submit')]";
        dropDownXpaths = Arrays.asList(xpath1, xpath2);*/
        //clickDropDownValue(dropDownXpaths, true);

        tearDown();

    }

    private static void tearDown() throws InterruptedException {
        Thread.sleep(10000);
        driver.quit();
    }


    private static void clickDropDownValue(List<String> dropDownXpaths, boolean clickOnEachStep) {

        for (int i = 0; i < dropDownXpaths.size(); i++) {

            String xpath = dropDownXpaths.get(i);

            //If last element , then click
            if (i == dropDownXpaths.size() - 1 || clickOnEachStep) {

                action.moveToElement(driver.findElement(By.xpath(xpath))).pause(2000).click().build().perform();
                System.out.println("Moved and clicked : " + xpath);
            } else {

                action.moveToElement(driver.findElement(By.xpath(xpath))).pause(2000).build().perform();
                System.out.println("Moved to element : " + xpath);
            }
        }

    }
}

