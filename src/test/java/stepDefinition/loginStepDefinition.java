package stepDefinition;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.io.IOException;
import pojoclass.TestcaseWrapper;
import utilities.JsonReader;
import pojoclass.ForgotPasswordRequest;
import pojoclass.JsonTestData;
import pojoclass.Login;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.testng.Assert.assertTrue;

public class loginStepDefinition extends Base  {
	
	   RequestSpecification request;
	   Response response;
	   Login loginRequest;
	   JsonTestData testData;
	   ForgotPasswordRequest forgotPasswordRequest;
	   
	   private static final Logger log = LoggerFactory.getLogger(loginStepDefinition.class);
	
	@Given("Admin creates POST request with valid credentials for the login API")
	public void admin_creates_post_request_with_valid_credentials_for_the_login_API()  throws IOException {
	  
		 request = createRequest();
		 TestcaseWrapper wrapper = getTestData();
		 testData = JsonReader.getTestDataByScenarioName("Login API", wrapper.getPostRequest());
		 
		loginRequest = testData.getLoginRequest();
		
	     request.body(loginRequest);
	     
	     log.info("Admin creates POST request with valid credentials - Postive Scenario");
		
	}

	@When("Admin sends a HTTPS POST request to the valid login endpoint for the login API")
	public void admin_sends_a_https_post_request_to_the_valid_login_endpoint_for_the_login_API() {
		 response = request
	                .when().log().all()
	                .post(testData.getEndpoint());
	}

	@Then("Admin receives {int} Created with auto generated token for the login API")
	public void admin_receives_created_with_auto_generated_token_for_the_login_API(Integer statusCode) {
		
		log.info("Validating the Actual Status Code for the login with Valid credentials: {}", response.getStatusCode());
		log.info("Validating the Expected Status Code for the login API with Valid Credentials: {}", testData.getexpectedStatusCode());
		 assertEquals(response.getStatusCode(), statusCode.intValue());
			log.info(response.asPrettyString());		
	}


@Then("Admin Validates response body matches JSON schema in Login API")
public void admin_validates_response_body_matches_json_schema_in_login_api() {

    log.info("Validating schema for login scenario");

    if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {

        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/loginSchema.json"));
}
}

@Then("Admin validates Login API response headers")
public void admin_validates_login_api_response_headers() {
	  log.info("Validating headers for login API");

	    String actualContentType = response.getHeader("Content-Type");
	    
	    assertTrue( actualContentType.contains("application/json"), "Expected Content-Type not found" + actualContentType );

}

@Then("Admin validates Login API response time")
public void admin_validates_login_api_response_time() {
	
	long responseTime = response.getTime();
	
	assertTrue( response.getTime() <= 3000, "API response time is greater than 3000");

	    log.info("Login API response time: {} ms", responseTime);

}

@Then("Admin validates Login API response all")
public void admin_validates_login_api_response_all() {

	log.info("Validating response body value in the response");
	
	    String type = response.jsonPath().getString("type");
	    String email = response.jsonPath().getString("email");
	    String status = response.jsonPath().getString("status");
	    
	assertTrue( response.jsonPath().getString("token") != null, "Token is missing");
	   assertEquals(type, "Bearer");
	    assertEquals(email, "Team06@gmail.com");
	    assertEquals(status, "ACTIVE");
}


@Given("Admin creates POST request for {string} for the login API")
public void admin_creates_post_request_for_for_the_login_API(String scenarioName) throws IOException  {
	
	log.info("Negative Scenario for the login API : {}", scenarioName);
	
	request = createRequest();
    TestcaseWrapper wrapper = getTestData();

    testData = JsonReader.getTestDataByScenarioName(scenarioName,wrapper.getPostRequest());

    request.contentType(testData.getContentType());

    if ("text/plain".equalsIgnoreCase(testData.getContentType())) {
        request.body(testData.getRawBody()); 

    } else if (scenarioName.equalsIgnoreCase("Login With Invalid Base URL")) {
        request = createInvalidBaseRequest();
  } 
    
    else if (testData.getLoginRequest() != null) {
        request.body(testData.getLoginRequest()); 
    }
  
}

@When("Admin sends request to the valid endpoint for the login API")
public void admin_sends_request_to_the_valid_endpoint_for_the_login_API() {

	 if (testData.getMethod().equalsIgnoreCase("GET")) {

	        response = request
	                .log().all()
	                .when()
	                .get(testData.getEndpoint());
	     log.info("Login API call with invalid method {} for {}",testData.getMethod(), testData.getTestcaseName());   
	        
	 }else {

	        response = request
	                .log().all()
	                .when()
	                .post(testData.getEndpoint());
	        
	        log.info("Forgot password confirm API call with valid method {} for {} ",testData.getMethod(), testData.getTestcaseName()); 
	 }
	 
}
	 

@Then("Admin receives the response for {string} for the login API")
public void admin_receives_the_response_for_for_the_login_API(String scenarioName) {
	
	log.info("Response Body:\n{}", response.asPrettyString());
	
	log.info("Validating the Actual Status Code  {} for the login API: {}", scenarioName,response.getStatusCode());
    
	log.info("Validating the Expected Status Code {} for the login API: {}", scenarioName, testData.getexpectedStatusCode());
	
	 assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
}

@Then("Admin Validates response body matches JSON schema in {string} Login API")
public void admin_validates_response_body_matches_json_schema_in_login_api(String scenarioName) {

    log.info("Validating schema for login scenario {}", scenarioName);

    if (scenarioName.equalsIgnoreCase("Login with invalid content type") ||
            scenarioName.equalsIgnoreCase("Login With Invalid Base URL") ||
            scenarioName.equalsIgnoreCase("Login With Invalid Endpoint")) {
    	
    	log.info("Login API response boby not found for the validation {} ", scenarioName);
    	
    	return;
    }
    
    
    if (response.getStatusCode() == 200 || response.getStatusCode() == 201) {

        response.then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("schema/loginSchema.json"));
}

}

@Then("Admin validates {string} Login API response headers")
public void admin_validates_login_api_response_headers(String scenarioName) {
	 log.info("Validating headers for login API {}",scenarioName);

	    String actualContentType = response.getHeader("Content-Type");
	    
	    
	    if (actualContentType == null || !actualContentType.contains("application/json")){
	    	
	    	log.info("Login API response with content rype {} ", actualContentType);
	    	
	    	return;
	    }
	    
	    assertTrue( actualContentType.contains("application/json"), "Expected Content-Type not found" + actualContentType );

}

@Then("Admin validates {string} Login API response time")
public void admin_validates_login_api_response_time(String scenarioName) {

	long responseTime = response.getTime();
	
	assertTrue( response.getTime() <= 3000, "API response time is greater than 3000");

	    log.info("Login API response time: {} ms for the scenario {} ", responseTime, scenarioName);


}

@Then("Admin validates {string} Login API response all")
public void admin_validates_login_api_response_all(String scenarioName) {
	log.info("Validating response Token value in the response {}", scenarioName);
	
	  if (scenarioName.equalsIgnoreCase("Login with invalid content type") ||
		        scenarioName.equalsIgnoreCase("Login With Invalid Base URL") ||
		        scenarioName.equalsIgnoreCase("Login With Invalid Endpoint")) {

		        log.info("Token is not generated for the scenario: {}", scenarioName);
		        return;
		    }
assertTrue( response.jsonPath().getString("token") == null, "Token is populated");
   

}

@Given("Admin creates forgot password POST request with {string}")
public void admin_creates_forgot_password_post_request_with(String scenarioName)throws IOException  {
	 request = createRequest();
	 TestcaseWrapper wrapper = getTestData();
	 testData = JsonReader.getTestDataByScenarioName(scenarioName, wrapper.getPostRequest());
	  
	 request.contentType(testData.getContentType());
	
	 
	    if ("text/plain".equalsIgnoreCase(testData.getContentType())) {
	        request.body(testData.getRawBody()); 

	    } else if (testData.getForgotPasswordRequest() != null) {
	    	   request.body(testData.getForgotPasswordRequest());
	    }
  
     
     log.info("Admin creates POST request for forgot password API {}", scenarioName);
}

@When("Admin sends a HTTPS request to the valid endpoint for forgot password API with {string}")
public void admin_sends_a_https_request_to_the_valid_endpoint_for_forgot_password_api_with(String scenarioName) {
    
	 
	 if (testData.getMethod().equalsIgnoreCase("GET")) {

	        response = request
	                .log().all()
	                .when()
	                .get(testData.getEndpoint());
	     log.info("Forgot password confirm API call with invalid method {} for {}",testData.getMethod(), scenarioName);   
	        
	 }else {

	        response = request
	                .log().all()
	                .when()
	                .post(testData.getEndpoint());
	        
	        log.info("Forgot password confirm API call with valid method {} for {} ",testData.getMethod(), scenarioName); 
	 }
	 
}

@Then("Admin validates forgot password response with {string}")
public void admin_validates_forgot_password_response_with(String scenarioName) {
   
	log.info("Response Body:\n{}", response.asPrettyString());
	
	log.info("Validating the Actual Status Code  {} for the forgot password confirmation API: {}", scenarioName,response.getStatusCode());
    
	log.info("Validating the Expected Status Code {} for the forgot password confirmation API: {}", scenarioName, testData.getexpectedStatusCode());
	
	 assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	
}

@Then("Admin Validates response body matches JSON schema in forgot password API")
public void admin_validates_response_body_matches_json_schema_in_forgot_password_api() {
	if (response.getStatusCode() == 201) {
	  try {      
		response.then().log().all()
	                .assertThat()
	                .body(matchesJsonSchemaInClasspath("schema/loginSchema.json"));
	      	        log.info("Schema validation Passed successfully for the forgot password confirmation API response");
		
	    } catch (AssertionError e) {

	        log.error("Schema validation Failed for the forgot password confirmation API response");
	        throw e; 
	    }
}
}
}



