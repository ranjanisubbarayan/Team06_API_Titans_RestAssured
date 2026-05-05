package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
		
		features = "src/test/resources/feature",
		glue = "stepDefinition",	
		plugin = {"pretty", "html:target/cucumber-report.html",
				  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	    		"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
	    		},
		dryRun = false
		
		)
public class TestRunner extends AbstractTestNGCucumberTests{

}

