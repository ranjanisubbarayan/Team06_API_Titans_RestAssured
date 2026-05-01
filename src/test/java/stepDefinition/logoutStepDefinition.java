package stepDefinition;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoclass.JsonTestData;
import pojoclass.TestcaseWrapper;
import utilities.JsonReader;
import utilities.Base;


public class logoutStepDefinition extends Base {
	
	  RequestSpecification request;
	   Response response;
	   JsonTestData testData;
	   
	   private static final Logger log = LoggerFactory.getLogger(logoutStepDefinition.class);
	   
	   
	   
	   @Given("Admin creates GET logout request for {string} in logout API")
	   public void admin_creates_get_logout_request_for_in_logout_api(String scenarioName) throws IOException  {
	    
			 TestcaseWrapper wrapper = getTestData();
			 testData = JsonReader.getTestDataByScenarioName(scenarioName, wrapper.getGetRequest());

			  if (scenarioName.equalsIgnoreCase("Logout with No Auth")) {
			        request = createRequest();
			  }
			 
			 else if (scenarioName.contains("Logout With Invalid Token")) {

			        request = createRequest()
			                .header("Authorization", "Bearerinvalidtoken");

			        log.info("Logout API request has been sent with {}",scenarioName );

			    }
			  else if (scenarioName.contains("Logout With Expired Token")) {

			        request = createRequest()
			                .header("Authorization", "Bearer expiredtoken");

			        log.info("Logout API request has been sent with {}",scenarioName );

			    }
			  else if (request == null) {
				    request = createTokenRequest();
				}

		    	log.info("Get request has been created for the LMS Logout API");
	   }

	   @When("Admin sends logout request for {string} in logout API")
	   public void admin_sends_logout_request_for_in_logout_api(String scenarioName) {
	      
		   if (testData.getMethod().equalsIgnoreCase("POST")) {

		        response = request
		                .log().all()
		                .when()
		                .post(testData.getEndpoint());
		     log.info("Logout API with invalid method {} for the scenario {} ",testData.getMethod(), scenarioName);   
		        
		 }else {

		        response = request
		                .log().all()
		                .when()
		                .get(testData.getEndpoint());
		        
		        log.info("Logout API with valid method {} for the scenario {}",testData.getMethod(),scenarioName); 
		 }
	   }

	   @Then("Admin validates logout response for {string} in logout API")
	   public void admin_validates_logout_response_for_in_logout_api(String scenarioName) {
		   
			
			 log.info("Response Body:\n{}", response.asPrettyString());	 
			 
			  assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
			  
			  log.info("Logout API request for {} with actual status code {}" , scenarioName ,testData.getexpectedStatusCode() );
	      
	   }


}
