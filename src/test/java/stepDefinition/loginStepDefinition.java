package stepDefinition;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
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
import utilities.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	

@Given("Admin creates POST request for {string} for the login API")
public void admin_creates_post_request_for_for_the_login_API(String scenarioName) throws IOException  {
	
	log.info("Negative Scenario for the login API : {}", scenarioName);
	
	request = createRequest();
    TestcaseWrapper wrapper = getTestData();

    testData = JsonReader.getTestDataByScenarioName(
            scenarioName,
            wrapper.getPostRequest()
    );

    request.contentType(testData.getContentType());

    if ("text/plain".equalsIgnoreCase(testData.getContentType())) {
        request.body(testData.getRawBody()); 

    } else if (testData.getLoginRequest() != null) {
        request.body(testData.getLoginRequest()); 
    }
  
}

@When("Admin sends request to the valid endpoint for the login API")
public void admin_sends_request_to_the_valid_endpoint_for_the_login_API() {

	 response = request
             .when().log().all()
             .post(testData.getEndpoint());
	 
}

@Then("Admin receives the response for {string} for the login API")
public void admin_receives_the_response_for_for_the_login_API(String scenarioName) {
	
	log.info("Response Body:\n{}", response.asPrettyString());
	
	log.info("Validating the Actual Status Code  {} for the login API: {}", scenarioName,response.getStatusCode());
    
	log.info("Validating the Expected Status Code {} for the login API: {}", scenarioName, testData.getexpectedStatusCode());
	
	 assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
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
