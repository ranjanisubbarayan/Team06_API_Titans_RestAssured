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
	
	@Given("Admin creates POST request with valid credentials")
	public void admin_creates_post_request_with_valid_credentials()  throws IOException {
	  
		 request = createRequest();
		 TestcaseWrapper wrapper = getTestData();
		 testData = JsonReader.getTestDataByScenarioName("Login API", wrapper.getPostRequest());
		 
		loginRequest = testData.getLoginRequest();
		
	     request.body(loginRequest);
		
	}

	@When("Admin sends a HTTPS POST request to the valid login endpoint")
	public void admin_sends_a_https_post_request_to_the_valid_login_endpoint() {
		 response = request
	                .when()
	                .post(testData.getEndpoint());
	}

	@Then("Admin receives {int} Created with auto generated token")
	public void admin_receives_created_with_auto_generated_token(Integer statusCode) {
		
		log.info("Validating status code...");
		 assertEquals(response.getStatusCode(), statusCode.intValue());
		 log.info(response.asString());
			log.info(response.asPrettyString());
		 
	   
		
	}



}
