package stepDefinition;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoclass.ProgramResponse;
import utilities.Base;
import utilities.JsonReader;
import utilities.RandomLetters;
import utilities.ScenarioContext;
import pojoclass.JsonTestData;
import pojoclass.ProgramRequest;
import pojoclass.TestcaseWrapper;

public class ProgramStepDefinition extends Base {

    RequestSpecification request;
    Response response;
    JsonTestData testData;
    ProgramRequest programRequest;

    List<ProgramResponse> programList;
    
    
    private static final Logger log = LoggerFactory.getLogger(ProgramStepDefinition.class);
    
    
    @Given("Admin sets Authorization to Bearer Token")
    public void admin_sets_authorization_to_bearer_token() throws IOException {
       
    	 request = createTokenRequest();
    	 
    }

    @Given("Admin creates GET Request for the LMS API")
    public void admin_creates_get_request_for_the_lms_api() {
     
    	log.info("Get reuest has been created for the LMS API");
    	
    }

    @When("Admin sends a HTTPS GET request to the valid all programs endpoint")
    public void admin_sends_a_https_get_request_to_the_valid_all_programs_endpoint() {
        

        response = request
                .when()
                .get("/allPrograms");
    }

    @Then("Admin receives {int} OK Status with response body")
    public void admin_receives_ok_status_with_response_body(Integer statusCode) {
    	log.info(response.asString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
    }

   

    @Given("Admin creates POST Request with valid request body")
    public void admin_creates_post_request_with_valid_request_body() {
    	
    	TestcaseWrapper wrapper = getTestData(); 

    	    testData = JsonReader.getTestDataByScenarioName(
    	            "Create Program",
    	            wrapper.getPostRequest()
    	    );
    	    
    	    programRequest = testData.getProgramRequest();
    	    
    	    String randomSuffix = RandomLetters.randomLetters(5);

    	    String uniqueName = programRequest.getProgramName() + "-" + randomSuffix;
    	    
    	    if (uniqueName.length() > 25) {
    	        uniqueName = uniqueName.substring(0, 25);
    	    }

    	    programRequest.setProgramName(uniqueName);
    	    
    	    request.body(programRequest);
    	    
    	    Response response = request.post("/saveprogram");
    	    int programId = response.jsonPath().getInt("programId");
    	    String programName = response.jsonPath().getString("programName");
    	    ScenarioContext.set("programId", programId);
    	    ScenarioContext.set("programName", programName);
    }

    @When("Admin sends a HTTPS request to the valid endpoint")
    public void admin_sends_a_https_request_to_the_valid_endpoint() {
    	response = request
                .when()
                .post(testData.getEndpoint());
    }

    @Then("Admin receives {int} Created Status with response body")
    public void admin_receives_created_status_with_response_body(Integer statusCode) {
     
    	log.info(response.asString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
    }
}






