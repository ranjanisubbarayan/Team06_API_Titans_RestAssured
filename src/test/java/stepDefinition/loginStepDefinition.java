package stepDefinition;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import java.io.IOException;
import pojoclass.TestcaseWrapper;
import utilities.JsonReader;
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





}
