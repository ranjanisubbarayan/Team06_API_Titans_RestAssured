package runner;

import io.cucumber.junit.Cucumber;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
		
		features = "src/test/resources/feature",
		glue = "stepDefinition",
		//tags = "@NegativeLoginAPI or @getallprogram",				
		plugin = {"pretty", "html:target/cucumber-report.html",
	    		//"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
	    		//"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
	    		},
		dryRun = true
		
		)
public class TestRunner{

}
