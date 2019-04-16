package learning.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SSCapture {

	public static String captureSS(WebDriver driver, String imageDir) throws IOException {
		
		TakesScreenshot scrShot =((TakesScreenshot)driver);
	    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	    File DestFile=new File(imageDir+".png");
	    FileUtils.copyFile(SrcFile, DestFile);
		
		return DestFile.getAbsolutePath();
	}

}
