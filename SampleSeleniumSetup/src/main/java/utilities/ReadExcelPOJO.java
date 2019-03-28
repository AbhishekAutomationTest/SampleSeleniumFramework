package utilities;

import java.util.HashMap;
import java.util.Map;

public class ReadExcelPOJO {
	
	
	String TestScript;
	String Description;
	String Execute;
	String ExecutionType;
	int NumberOfIterations;
	int StartIndexForIterations;
	int MobileConfigurations;
	String LOB;
	Map<ReadExcelPOJO,ReadExcelPOJO> pojoMap = new HashMap<ReadExcelPOJO,ReadExcelPOJO>();

	String BusinessModule;
	public String getBusinessModule() {
		return BusinessModule;
	}
	public void setBusinessModule(String businessModule) {
		BusinessModule = businessModule;
	}
	public String getTestScript() {
		return TestScript;
	}
	public void setTestScript(String testScript) {
		TestScript = testScript;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getExecute() {
		return Execute;
	}
	public void setExecute(String execute) {
		Execute = execute;
	}
	public String getExecutionType() {
		return ExecutionType;
	}
	public void setExecutionType(String executionType) {
		ExecutionType = executionType;
	}
	public int getNumberOfIterations() {
		return NumberOfIterations;
	}
	public void setNumberOfIterations(int numberOfIterations) {
		NumberOfIterations = numberOfIterations;
	}
	public int getStartIndexForIterations() {
		return StartIndexForIterations;
	}
	public void setStartIndexForIterations(int startIndexForIterations) {
		StartIndexForIterations = startIndexForIterations;
	}
	public int getMobileConfigurations() {
		return MobileConfigurations;
	}
	public void setMobileConfigurations(int mobileConfigurations) {
		MobileConfigurations = mobileConfigurations;
	}
	public String getLOB() {
		return LOB;
	}
	public void setLOB(String lOB) {
		LOB = lOB;
	}
	
}
