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
import pojoclass.ResetPasswordRequest;


public class resetPasswordStepdefinition extends Base {

	  RequestSpecification request;
	   Response response;
	   JsonTestData testData;
	   ResetPasswordRequest resetPasswordRequest ;
	   
	   private static final Logger log = LoggerFactory.getLogger(resetPasswordStepdefinition.class);
	

@Given("Admin creates POST request for {string} reset password API")
public void admin_creates_post_request_for_reset_password_api(String scenarioName) throws IOException {
	
	request = createTokenRequest();
	
	TestcaseWrapper wrapper = getTestData();

    testData = JsonReader.getTestDataByScenarioName(scenarioName, wrapper.getResetPasswordRequest()
    );
    
    

    if (scenarioName.equalsIgnoreCase("Reset Password Without Authentication")) {

        request = createRequest();

    } else if (scenarioName.equalsIgnoreCase("Reset Password With Invalid Token")) {

        request = createRequest()
                .header("Authorization", "Bearer.invalidtoken");

    } else if (scenarioName.equalsIgnoreCase("Reset Password With Expired Token")) {

        request = createRequest()
                .header("Authorization", "Bearer.expiredtoken");

    } 
    
    else if (request == null){

        request = createTokenRequest();
    }


    if ("text/plain".equalsIgnoreCase(testData.getContentType())) {
        request.body(testData.getRawBody());
    } else if (testData.getResetPasswordRequest() != null) {
        request.body(testData.getResetPasswordRequest());
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