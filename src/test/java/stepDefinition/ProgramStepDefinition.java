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
import utilities.ScenarioContext;
import pojoclass.JsonTestData;
import pojoclass.ProgramRequest;
import pojoclass.TestcaseWrapper;

public class ProgramStepDefinition extends Base {

    RequestSpecification request;
    Response response;
    JsonTestData testData;
    ProgramRequest programRequest;

    List<ProgramResponse> programResponseasList;
    
    
    private static final Logger log = LoggerFactory.getLogger(ProgramStepDefinition.class);
    
    
    @Given("Admin sets Authorization to Bearer Token")
    public void admin_sets_authorization_to_bearer_token() throws IOException {
       
    	 request = createTokenRequest();
    	 
    }

    @Given("Admin creates GET Request for the LMS Program API")
    public void admin_creates_get_request_for_the_lms_program_api() {
    	
    	TestcaseWrapper wrapper = getTestData();

    	testData = JsonReader.getTestDataByScenarioName("Get All Programs", wrapper.getGetRequest());

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
        
        assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
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

    @Then("Admin receives Created Status with response body in program API")
    public void admin_receives_created_status_with_response_body_in_program_api() {
     
    	log.info("Response Body:\n{}", response.asString());
    	
		if (response.getStatusCode() == testData.getexpectedStatusCode()) {
			int programId = response.jsonPath().getInt("programId");
			String programName = response.jsonPath().getString("programName");
			ScenarioContext.set("programId", programId);
			ScenarioContext.set("programName", programName);
		}
    	assertEquals(response.getStatusCode(),testData.getexpectedStatusCode());
    	
    	log.info("admin received the response body for the add program module with the Actual Status Code: {}", response.getStatusCode());
    	
    	log.info("admin received the response body for the add program module with the Expected Status Code: {}", testData.getexpectedStatusCode());
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
    
    

@Given("Admin creates PUT request with {string} for update program API")
public void admin_creates_put_request_with_for_update_program_api(String scenarioName) {
   
	   TestcaseWrapper wrapper = getTestData();

	    testData = JsonReader.getTestDataByScenarioName(
	            scenarioName,
	            wrapper.getPutRequest()
	    );
	    
	    programRequest = testData.getProgramRequest();
	    
	    if (!"none".equalsIgnoreCase(testData.getbodyType())) {
	    
	    String randomSuffix = RandomLetters.randomLetters(5);

	    String uniqueName = programRequest.getProgramName() + "-" + randomSuffix;
	    
	    if (uniqueName.length() > 25) {
	        uniqueName = uniqueName.substring(0, 25);
	    }

	    programRequest.setProgramName(uniqueName);
	    
	    request.body(programRequest);
	    }
	    
	    log.info("admin creates PUT request for the Update Program Module with the valida request body {}", scenarioName);
}

@When("Admin sends PUT request for {string} with valid endpoint for update program API")
public void admin_sends_put_request_for_with_valid_endpoint_for_update_program_api(String scenarioName) {

    Integer programId = ScenarioContext.get("programId", Integer.class);

    String endpoint = testData.getEndpoint();
    
    if (endpoint.contains("{programId}")) 
    {
    	 endpoint = endpoint.replace("{programId}", String.valueOf(programId));
    }
    
    
    if (testData.getMethod().equalsIgnoreCase("POST")) {

        response = request
                .log().all()
                .when()
                .post(endpoint);
     log.info("Update program with invalid method {} for the scenario {} ",testData.getMethod(), scenarioName);   
        
 }else {

        response = request
                .log().all()
                .when()
                .put(endpoint);
        
        log.info("update program with valid method {} for the scenario {}",testData.getMethod(),scenarioName); 
 }
    
    
    
}

@Then("Admin validates response for {string} for update program API")
public void admin_validates_response_for_for_update_program_api(String scenarioName) {
	 log.info("Response Body:\n{}", response.asPrettyString());	 
	 
	  assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	  
	  log.info("Validatinf delete program request for {} with actual status code {}" , scenarioName ,testData.getexpectedStatusCode() );
}


@Then("Admin Validates response body matches JSON schema in  in Update program API")
public void admin_validates_response_body_matches_json_schema_in_in_update_program_api() {
	try {
		if (response.getStatusCode() == 200) {

		    response.then()
		        .assertThat()
		        .body(matchesJsonSchemaInClasspath("schema/addprogramSchema.json"));

		    log.info("Schema validation Passed successfully for the Update progam response");
		}
	        
	    } catch (AssertionError e) {

	        log.error("Schema validation Failed for the Update progam response");
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
	
	assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
     log.info("admin received the response body with the Status Code: {}", response.getStatusCode());
 	log.info("Response Body:\n{}", response.asPrettyString());
 
}



@Given("Admin creates delete request with {string} for the delete program API")
public void admin_creates_delete_request_with_for_the_delete_program_api(String scenarioName)throws IOException  {
	
	   TestcaseWrapper wrapper = getTestData();

	    testData = JsonReader.getTestDataByScenarioName(
	            scenarioName,
	            wrapper.getDeleteRequest()
	    );
	    
	    request = createTokenRequest(); 
        log.info("Delete Program Request created WITH Authorization for {}", scenarioName );
   
}

@When("Admin sends DELETE request for {string} for the delete program API")
public void admin_sends_delete_request_for_for_the_delete_program_api(String endpointscenario) {
	
	 Integer programId = ScenarioContext.get("programId", Integer.class);
	  
	 String endpoint = testData.getEndpoint();
	 
    if (endpoint.contains("{programId}")) {
		    endpoint = endpoint.replace("{programId}", String.valueOf(programId));
		}
	 
	 if (testData.getMethod().equalsIgnoreCase("POST")) {

	        response = request
	                .log().all()
	                .when()
	                .post(endpoint);
	     log.info("Delete program with invalid method {}",testData.getMethod());   
	        
	 }else {

	        response = request
	                .log().all()
	                .when()
	                .delete(endpoint);
	        
	        log.info("Delete program with valid method {}",testData.getMethod()); 
	 }
	 
	 log.info("Delete program Request has been sent to endpoint : {}", endpoint);
}

@Then("Admin validates response for {string} for the delete program API")
public void admin_validates_response_for_for_the_delete_program_api(String scenarioName) {
	
	
	 log.info("Response Body:\n{}", response.asPrettyString());	 
	 
	  assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	  
	  log.info("Validatinf delete program request for {} with actual status code {}" , scenarioName ,testData.getexpectedStatusCode() );
   
	 
}

@Given("Admin creates GET request for {string} in program API")
public void admin_creates_get_request_for_in_program_api(String scenarioName) throws IOException  {
    
	TestcaseWrapper wrapper = getTestData();

	testData = JsonReader.getTestDataByScenarioName(scenarioName, wrapper.getGetRequest());
	
    if ((scenarioName.equalsIgnoreCase("Get Program By ID Invalid BaseURI"))) {
        request = createInvalidBaseRequest();
        
        log.info("GET Program API request has been sent with {}",scenarioName );
        
    } else if ((scenarioName.equalsIgnoreCase("Without Auth"))) {
        request = createRequest();
        
        log.info("GET Program API request has been sent with {}",scenarioName );
    } else if (request == null){
        request = createTokenRequest();
        
        log.info("GET Program API request has been sent with {}",scenarioName );
    }

              
}

@When("Admin sends GET request for {string} in program API")
public void admin_sends_get_request_for_in_program_api(String scenarioName) {
   
	 Integer programId = ScenarioContext.get("programId", Integer.class);
	  
	 String endpoint = testData.getEndpoint();
	 
    if (endpoint.contains("{programId}")) {
		    endpoint = endpoint.replace("{programId}", String.valueOf(programId));
		}
	 
    
	 if (testData.getMethod().equalsIgnoreCase("POST")) {

	        response = request
	                .log().all()
	                .when()
	                .post(endpoint);
	     log.info("GET Program API with invalid method {} for the scenario {} ",testData.getMethod(), scenarioName);   
	        
	 }else {

	        response = request
	                .log().all()
	                .when()
	                .get(endpoint);
	        
	        log.info("GET Program API with valid method {} for the scenario {}",testData.getMethod(),scenarioName); 
	 }
}

@Then("Admin validates GET response for {string} in program API")
public void admin_validates_get_response_for_in_program_api(String scenarioName) {
   
	
	 log.info("Response Body:\n{}", response.asPrettyString());	 
	 
	  assertEquals(response.getStatusCode(), testData.getexpectedStatusCode());
	  
	  log.info("Validatinf delete program request for {} with actual status code {}" , scenarioName ,testData.getexpectedStatusCode() );
}

}






