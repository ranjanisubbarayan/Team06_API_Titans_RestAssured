package stepDefinition;

import static org.testng.Assert.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
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

    @Given("Admin creates GET Request for the LMS Program API")
    public void admin_creates_get_request_for_the_lms_program_api() {
    	
    	TestcaseWrapper wrapper = getTestData();

    	testData = JsonReader.getTestDataByScenarioName(
    	        "Get All Programs",
    	        wrapper.getGetRequest()
    	);

    	log.info("Get request has been created for the LMS API");
    	
    }

    @When("Admin sends a HTTPS GET request to the valid all programs endpoint in program API")
    public void admin_sends_a_https_get_request_to_the_valid_all_programs_endpoint_in_program_api() {
        
    	
        response = request
                .when()
                .get(testData.getEndpoint());
        
        log.info("Get request has been sent for the LMS API");
    }

    @Then("Admin receives Status with response body in GET program API")
    public void admin_receives_status_with_response_body_in_get_program_api() {
        
        assertEquals(response.getStatusCode(), testData.getExpectedStatusCode());
        log.info("admin received the response body with the Status Code: {}", response.getStatusCode());
    	log.info("Response Body:\n{}", response.asPrettyString());
    }	
   @Then("Admin Validates response body matches JSON schema in  in GET program API")
    public void admin_validates_response_body_matches_json_schema_in_in_get_program_api() {
    
    	try {
   	       
  	        response.then().log().all()
  	                .assertThat()
  	                .body(matchesJsonSchemaInClasspath("schema/allprogramschema.json"));
  	      	        log.info("Schema validation Passed successfully for the getallprogam response");

  	    } catch (AssertionError e) {

  	        log.error("Schema validation Failed for the getallprogam response");
  	        throw e; 
  	    }
    	
   }


   

    @Given("Admin creates POST Request with valid request body in program API")
    public void admin_creates_post_request_with_valid_request_body_in_program_api() {
    	
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
    	    
    	    log.info("admin creates post request for the Program Module with the valida request body");
    	   
    }

    @When("Admin sends a HTTPS request to the valid endpoint in program API")
    public void admin_sends_a_https_request_to_the_valid_endpoint_in_program_api() {
    	response = request
                .when()
                .post(testData.getEndpoint());
    	log.info("Admin sends a HTTPS request for the Program Module with a valid endpoint");
    	
    }

    @Then("Admin receives {int} Created Status with response body in program API")
    public void admin_receives_created_status_with_response_body_in_program_api(Integer statusCode) {
     
    	log.info("Response Body:\n{}", response.asString());
    	
    	assertEquals(response.getStatusCode(), statusCode.intValue());
    	log.info("admin received the response body for the add program module with the Status Code: {}", response.getStatusCode());
    }
    @Then("Admin Validates response body matches JSON schema in  in POST program API")
    public void admin_validates_response_body_matches_json_schema_in_in_post_program_api() {
  	 
    	try {
  	       
  	        response.then()
  	                .assertThat()
  	                .body(matchesJsonSchemaInClasspath("schema/addprogramSchema.json"));
  	        log.info("Schema validation Passed successfully for the addprogam response");

  	    } catch (AssertionError e) {

  	        log.error("Schema validation Failed for the addprogam response");
  	        throw e; 
  	    }
    	
    }
    

@Given("Admin creates DELETE Request with valid program ID in endpoints in program API")
public void admin_creates_delete_request_with_valid_program_id_in_endpoints_in_program_api() {
 
	TestcaseWrapper wrapper = getTestData();

	testData = JsonReader.getTestDataByScenarioName(
	        "DeleteProgram ByID",
	        wrapper.getDeleteRequest()
	);

	log.info("DELETE Request has been sent with valid program ID in endpoints for program API");
}

@When("Admin sends a HTTPS DELETE request to the valid endpoint in program API")
public void admin_sends_a_https_delete_request_to_the_valid_endpoint_in_program_api() {
    
	
    response = request
            .when().log().all()
            .delete(testData.getEndpoint());
    
    log.info("Delete request has been sent for the LMS API");
}

@Then("Admin receives Status with response body in DELETE program API")
public void admin_receives_status_with_response_body_in_delete_program_api() {
	
	assertEquals(response.getStatusCode(), testData.getExpectedStatusCode());
     log.info("admin received the response body with the Status Code: {}", response.getStatusCode());
 	log.info("Response Body:\n{}", response.asPrettyString());
 
}
}






