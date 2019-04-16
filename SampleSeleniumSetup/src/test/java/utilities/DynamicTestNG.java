package utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class DynamicTestNG {
	
	ReadExcel read = new ReadExcel();
	
	public void runTestNGTest(Map<String,String> testNGParams) {
		
		//Object of TestNG
		TestNG myTestNG = new TestNG();
		
		//Create instance of XML Suite.
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName(testNGParams.get("ScenarioName")); 
	    mySuite.setParallel(XmlSuite.ParallelMode.METHODS); 
		
	    //create instance of XML Test.
	    XmlTest myTest = new XmlTest(mySuite);
		myTest.setName(testNGParams.get("ScenarioName"));
		//Assign parameter(Map<String,String>) of choice to set in Test.
		myTest.setParameters(testNGParams);
		
		//Create List of XML Class
		List<XmlClass> myClasses = new ArrayList<XmlClass>();
		
		//Add XML Class into it which we want to run
		
		myClasses.add(new XmlClass (testNGParams.get("ClassName")));
		myTest.setXmlClasses(myClasses);
		
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		myTests.add(myTest);
		
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		mySuites.add(mySuite);
		
		myTestNG.setXmlSuites(mySuites);
		mySuite.setFileName("myTemp.xml");
		mySuite.setThreadCount(3);
		myTestNG.run();
		
		for(XmlSuite suite : mySuites) 
	     {  
	         createXmlFile(suite); 
	     }   
	     System.out.println("Filerated successfully.");  
		
	}
	
	public void createXmlFile(XmlSuite mSuite) 
	{
		FileWriter writer; 
	       try { 
	            writer = new FileWriter(new File("myTemp.xml")); 
	            writer.write(mSuite.toXml()); 
	            writer.flush(); 
	            writer.close(); 
	            System.out.println(new File("myTemp.xml").getAbsolutePath());
	       }
	       catch(IOException e) {
	    	   e.printStackTrace();
	       }
	}
}
