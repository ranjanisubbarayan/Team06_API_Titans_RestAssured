package stepDefinition;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojoclass.JsonTestData;
import pojoclass.TestcaseWrapper;
import utilities.JsonReader;
import utilities.Token;
import pojoclass.ResetPasswordRequest;


public class resetPasswordStepdefinition extends Base {

	  RequestSpecification request;
	   Response response;
	   JsonTestData testData;
	   ResetPasswordRequest resetPasswordRequest ;
	   
	   private static final Logger log = LoggerFactory.getLogger(resetPasswordStepdefinition.class);
	   
	   
	   @Given("Admin creates POST request with valid credentials for the reset API")
	   public void admin_creates_post_request_with_valid_credentials_for_the_reset_api() throws IOException {
		 
			 TestcaseWrapper wrapper = getTestData();
			 testData = JsonReader.getTestDataByScenarioName("Reset Password With Valid Token", wrapper.getResetPasswordRequest());
			 
			 resetPasswordRequest = testData.getResetPasswordRequest();
			 request = createRequest();

			 request = request
			     .header("Content-Type", "application/json")
			     .header("Authorization", "Bearer " + Token.token)
			     .body(resetPasswordRequest);

		     
		     log.info("Admin creates POST request with valid credentials - Postive Scenario");
	   }

	   @When("Admin sends a HTTPS POST request to the valid login endpoint for the reset API")
	   public void admin_sends_a_https_post_request_to_the_valid_login_endpoint_for_the_reset_api() {
			 response = request
		                .when().log().all()
		                .post(testData.getEndpoint());
	   }

	   @Then("Admin receives {int} Created with auto generated token for the reset API")
	   public void admin_receives_created_with_auto_generated_token_for_the_reset_api(Integer statusCode) {
		   log.info("Validating the Actual Status Code for the Reset password with Valid credentials: {}", response.getStatusCode());
			log.info("Validating the Expected Status Code for the Reset password API with Valid Credentials: {}", testData.getexpectedStatusCode());
			 assertEquals(response.getStatusCode(), statusCode.intValue());
				log.info(response.asPrettyString());	
	   }
	

@Given("Admin creates POST request for {string} reset password API")
public void admin_creates_post_request_for_reset_password_api(String scenarioName) throws IOException {
	
	request = createTokenRequest(); 
	
	TestcaseWrapper wrapper = getTestData();

    testData = JsonReader.getTestDataByScenarioName(scenarioName, wrapper.getResetPasswordRequest());
    

   if (scenarioName.equalsIgnoreCase("Reset Password Without Authentication")) {

        request = createRequest();

    } else if (scenarioName.equalsIgnoreCase("Reset Password With Empty Token")) {

        request = createRequest()
                .header("Authorization", "Bearer ");
        
    }
    
    else if (scenarioName.equalsIgnoreCase("Reset Password With Invalid Token")) {

        request = createRequest()
                .header("Authorization", "Bearer.invalidtoken");

    } else if (scenarioName.equalsIgnoreCase("Reset Password With Expired Token")) {

        request = createRequest()
                .header("Authorization", "Bearer.expiredtoken");

    } 
    
    else if (request == null){

        request = createTokenRequest();
    }




    log.info("Reset password request created for scenario: {}", scenarioName);
}

@When("Admin sends POST request for {string} reset password API")
public void admin_sends_post_request_for_reset_password_api(String scenarioName) {
  
	if (testData.getMethod().equalsIgnoreCase("POST")) {

        response = request
                .log().all()
                .when()
                .post(testData.getEndpoint());
     log.info("Rest Password API with Valid method {} for the scenario {} ",testData.getMethod(), scenarioName);   
        
 }else {

        response = request
                .log().all()
                .when()
                .get(testData.getEndpoint());
        
        log.info("Rest Password API with Invalid method {} for the scenario {}",testData.getMethod(),scenarioName); 
 }
}

@Then("Admin validates POST response for {string} reset password API")
public void admin_validates_post_response_for_reset_password_api(String scenarioName) {

	 log.info("Response Body:\n{}", response.asPrettyString());	 
	 
	  assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	  
	  log.info("Logout API request for {} with actual status code {}" , scenarioName ,testData.getexpectedStatusCode() );
 
	
}

}