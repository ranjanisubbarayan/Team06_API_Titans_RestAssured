package stepDefinition;

import static org.testng.Assert.assertEquals;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoclass.BatchDataPojo;
import pojoclass.JsonTestData;
import pojoclass.Login;
import pojoclass.TestcaseWrapper;
import utilities.Base;
import utilities.JsonReader;
import utilities.RandomLetters;
import utilities.ScenarioContext;

public class BatchStepDefiniton extends Base {
	
	Scenario scenario; 
	RequestSpecification request;
    Response response;
    BatchDataPojo batchData;
    Login loginRequest;

    ScenarioContext context = new ScenarioContext();

    JsonTestData testData;
    private static final Logger log = LoggerFactory.getLogger(BatchStepDefiniton.class);

    @Given("Admin sets Authorization to Bearer Token for batch")
    public void admin_sets_authorization_to_bearer_token_for_batch() throws IOException {
    	request = createTokenRequest();
    }

	@Given("Admin creates POST request with mandatory and optional fields")
	public void admin_creates_post_request_with_mandatory_and_optional_fields() throws IOException {
		System.out.println("Inside POST request");
		TestcaseWrapper wrapper = getTestData(); 
		testData = JsonReader.getTestDataByScenarioName(
	            "create batch with all fields",
	            wrapper.getTests()
	    );
		batchData = testData.getBatchData();
		int programId = ScenarioContext.get("programId", Integer.class);
	    String programName1 = ScenarioContext.get("programName", String.class);
	    
	    batchData.setProgramId(programId);
	    batchData.setProgramName(programName1);
	    System.out.println(programName1 + ", programid:" + programId);
	    String randomSuffix = RandomLetters.randomNumber(5);
		batchData.setBatchName(programName1 + "_" + randomSuffix);
		request.contentType(testData.getContentType())
		.body(batchData);
//		
	    
	}

	@When("Admin sends HTTPS request to the endpoint")
	public void admin_sends_https_request_to_the_endpoint() {
		
		response = request
                .when()
                .post(testData.getEndpoint());
		String batchId = response.jsonPath().getString("batchId");
		String batchName = response.jsonPath().getString("batchName");
		ScenarioContext.set("createdBatchId",batchId);
		System.out.println("Created Batch ID: " + batchId);
		ScenarioContext.set("createdBatchName",batchName);
		System.out.println("Created Batch Name: " + batchName);
//		System.out.println(response.getBody().asPrettyString());
	}

	@Then("Admin receives {int} Created status with response body.")
	public void admin_receives_created_status_with_response_body(Integer statusCode) {
	    
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());

	}

	@Given("Admin creates a POST request with program name that does not match the associated program id")
	public void admin_creates_a_post_request_with_program_name_that_does_not_match_the_associated_program_id() {
		
		TestcaseWrapper wrapper = getTestData(); 
		testData = JsonReader.getTestDataByScenarioName(
	            "create batch program name that does not match the associated program id",
	            wrapper.getTests()
	    );
		batchData = testData.getBatchData();
//		int programId = ScenarioContext.get("programId", Integer.class);
	    String programName = ScenarioContext.get("programName", String.class);
	    
//	    batchData.setProgramId(programId);
	    batchData.setProgramName(programName);
	    String randomSuffix = RandomLetters.randomNumber(5);
		batchData.setBatchName(programName + "_" + randomSuffix);
	    request.contentType(testData.getContentType())
		.body(batchData);
	    
	}

	@Then("Admin receives a {int} created and the response body contains the program details corresponding to the provided program id")
	public void admin_receives_a_created_and_the_response_body_contains_the_program_details_corresponding_to_the_provided_program_id(Integer statusCode) {
	    
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
//    	assertEquals(response.getStatusCode(), testData.getExpectedStatus());
	    
	}

	@Given("Admin creates POST request with only optional fields in request body")
	public void admin_creates_post_request_with_only_optional_fields_in_request_body() {
	    
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		request.contentType(testData.getContentType())
		.body(batchData);
	   
	}

	@Then("Admin receives {int} Bad Request with valid error message")
	public void admin_receives_bad_request_with_valid_error_message(Integer statusCode) {
		
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
	    
	}

	@Given("Admin creates POST request with only mandatory fields in request body")
	public void admin_creates_post_request_with_only_mandatory_fields_in_request_body() {
	    
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		request.contentType(testData.getContentType())
		.body(batchData);
	}

	@Given("Admin creates POST request with {string}")
	public void admin_creates_post_request_with(String testcaseName) {
		
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
				testcaseName,
	            wrapper.getTests()
	    );
		batchData = testData.getBatchData();
		request.contentType(testData.getContentType())
		.body(batchData);
		int programId = ScenarioContext.get("programId", Integer.class);
		String programName = ScenarioContext.get("programName", String.class);
		batchData.setProgramId(programId);
		batchData.setProgramName(programName);
		
		String invalidBatchName = null;
		switch(testcaseName) {
		case "without underscore in batch name":
			invalidBatchName = programName + "435";
			break;
		case "with hyphen in batch name":
			invalidBatchName = (programName + "-435");
			break;
		case "with characters in the suffix of batch name":
			invalidBatchName = (programName + "ABD");
			break;
		case "with special characters in the suffix of batch name":
			invalidBatchName = (programName + "%^$");
			break;
		case "with batch name length more than 28 characters":
			invalidBatchName = (programName + "_2345678901234567890123");
			break;
		case "with batch name length less than 6 characters":
			invalidBatchName = (programName + "-3");
			break;
		case "with duplicate batch name":
			invalidBatchName = (ScenarioContext.get("existingBatchName", String.class));
			break;
			
		}
		batchData.setBatchName(invalidBatchName);
		request.contentType(testData.getContentType())
		.body(batchData);
		
	}

	@Given("Admin creates POST request with {string} for batch description")
	public void admin_creates_post_request_with_for_batch_description(String testcaseName) {
	    
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
				testcaseName,
	            wrapper.getTests()
	    );
		batchData = testData.getBatchData();
		
		int programId = ScenarioContext.get("programId", Integer.class);
		String programName = ScenarioContext.get("programName", String.class);
		batchData.setProgramId(programId);
		batchData.setProgramName(programName);
		String randomSuffix = RandomLetters.randomNumber(5);
		batchData.setBatchName(programName + "_" + randomSuffix);
		request.contentType(testData.getContentType())
		.body(batchData);
	}

	@Given("Admin creates POST request with {string} in status field")
	public void admin_creates_post_request_with_in_status_field(String testcaseName) {
	   
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
				testcaseName,
	            wrapper.getTests()
	    );
		batchData = testData.getBatchData();
		
		int programId = ScenarioContext.get("programId", Integer.class);
		String programName = ScenarioContext.get("programName", String.class);
		batchData.setProgramId(programId);
		batchData.setProgramName(programName);
		String randomSuffix = RandomLetters.randomNumber(5);
		batchData.setBatchName(programName + "_" + randomSuffix);
		request.contentType(testData.getContentType())
		.body(batchData);
		
	}

	@Given("Admin creates POST request with {string} in number of classes field")
	public void admin_creates_post_request_with_in_number_of_classes_field(String testcaseName) {
	   
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
				testcaseName,
	            wrapper.getTests()
	    );
		batchData = testData.getBatchData();
		
		int programId = ScenarioContext.get("programId", Integer.class);
		String programName = ScenarioContext.get("programName", String.class);
		batchData.setProgramId(programId);
		batchData.setProgramName(programName);
		String randomSuffix = RandomLetters.randomNumber(5);
		batchData.setBatchName(programName + "_" + randomSuffix);
		if(testcaseName.equals("with non numeric in number of classes")) {
			request.contentType(testData.getContentType())
			.body("{" + "\"batchDescription\":\"" + batchData.getBatchDescription() + "\","
	                   + "\"batchName\":\"" + batchData.getBatchName() + "\","
	                   + "\"batchStatus\":\"" + batchData.getBatchStatus() + "\","
	                   + "\"programId\":" + programId + ","
	                   + "\"programName\":\"" + programName + "\","
	                   + "\"batchNoOfClasses\":\"AD\""
	                   + "}");
			return;
		}
		request.contentType(testData.getContentType())
		.body(batchData);
	}

	@Given("Admin creates POST request with inactive program id")
	public void admin_creates_post_request_with_inactive_program_id() {
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		request.contentType(testData.getContentType())
		.body(batchData);
	    
	}

	@Given("Admin creates POST request with program id that is not exist in the system")
	public void admin_creates_post_request_with_program_id_that_is_not_exist_in_the_system() {
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		request.contentType(testData.getContentType())
		.body(batchData);
	    
	}

	@Then("Admin receives {int} not found with valid error message")
	public void admin_receives_not_found_with_valid_error_message(Integer statusCode) {
	    
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
	    
	}

	@Given("Admin creates POST request with valid request body")
	public void admin_creates_post_request_with_valid_request_body() {
	    
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		request.contentType(testData.getContentType())
		.body(batchData);
	    
	}

	@When("Admin sends HTTPS request to the invalid endpoint")
	public void admin_sends_https_request_to_the_invalid_endpoint() {
		response = request
                .when()
                .post(testData.getEndpoint());
	}

	@Given("Admin creates POST request with invalid content type")
	public void admin_creates_post_request_with_invalid_content_type() throws JsonProcessingException {
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(batchData);
		request.header("Content-Type",testData.getContentType());
		request.body(jsonBody);
	    
	}

	@When("Admin sends a HTTPS request to the valid endpoint for batch")
	public void admin_sends_a_https_request_to_the_valid_endpoint_for_batch() {
		response = request
                .when()
                .post(testData.getEndpoint());  
	}

	@Then("Admin receives {int} unsupported media type")
	public void admin_receives_unsupported_media_type(Integer statusCode) {
	    
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
    	int status = response.getStatusCode();
    	String error = response.jsonPath().getString("error");

    	System.out.println("Status: " + status);
    	System.out.println("Error: " + error);
	}

	@Given("Admin creates GET request with valid request body")
	public void admin_creates_get_request_with_valid_request_body() throws JsonProcessingException {
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(batchData);
		request.header("Content-Type", testData.getContentType());	
		request.body(jsonBody);
	    
	}

	@When("Admin sends GET HTTPS request to the valid endpoint")
	public void admin_sends_get_https_request_to_the_valid_endpoint() {
	    
		response = request
                .when()
                .get(testData.getEndpoint());
	}

	@Then("Admin receives {int} method not allowed")
	public void admin_receives_method_not_allowed(Integer statusCode) {
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
    	int status = response.getStatusCode();
    	String error = response.jsonPath().getString("error");

    	System.out.println("Status: " + status);
    	System.out.println("Error: " + error);
	    
	}

//	---------------------------------------Post request with No Auth-------------------------------------------
	@Given("Admin creates POST request with No Auth")
	public void admin_creates_post_request_with_no_auth() {
		TestcaseWrapper wrapper = getTestData();
//		testData = JsonReader.getTestDataByScenarioName(
//		        Hooks.scenario.getName(),
//		        wrapper.getTests()
//		);
//		loginRequest = testData.getLoginRequest();
//		request = given()
//	            .header("Content-Type", testData.getContentType())
//	            .body(loginRequest);
		testData = JsonReader.getTestDataByScenarioName("No Auth Login", wrapper.getTests());
		request = given()
	            .header("Content-Type", "application/json")
	            .body("{}");
	    
	}
	
	@When("Admin sends a HTTPS POST request to the valid login endpoint")
	public void admin_sends_a_https_post_request_to_the_valid_login_endpoint() {
	    
		response = request.when().post(testData.getEndpoint());
	}

	@Given("Admin creates POST request without authorization")
	public void admin_creates_post_request_without_authorization() {
	    
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		request = given().header("Content-Type", testData.getContentType())
		.body(batchData);
	    
	}

	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_unauthorized(Integer statusCode) {
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
	    
	}
	
//----------------------------------------------Get All Batches--------------------------------------
	
//	@Given("Admin sets Authorization to Bearer Token.")
//	public void admin_sets_authorization_to_bearer_token() {
//	   
//	   
//	}

	@Given("Admin creates GET request")
	public void admin_creates_get_request() throws JsonProcessingException {
	   
		TestcaseWrapper wrapper = getTestData();

    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
	}
	@When("Admin sends HTTPS request to the endpoint for get request")
	public void admin_sends_https_request_to_the_endpoint_for_get_request() {
	   
		response = request
                .when()
                .get(testData.getEndpoint());
	}

	@Then("Admin receives {int} OK Status with response body.")
	public void admin_receives_ok_status_with_response_body(Integer statusCode) {
	   
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
  
	}

	@Then("Admin receives {int} Not Found with error message")
	public void admin_receives_not_found_with_error_message(Integer statusCode) {
	   
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
    	int status = response.getStatusCode();
    	String error = response.jsonPath().getString("error");

    	System.out.println("Status: " + status);
    	System.out.println("Error: " + error);
	}

	@Given("Admin creates POST request")
	public void admin_creates_post_request() {
	   
		TestcaseWrapper wrapper = getTestData();

    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
	}

	@When("Admin sends POST HTTPS request to the endpoint")
	public void admin_sends_post_https_request_to_the_endpoint() {
	   
		response = request
                .when()
                .post(testData.getEndpoint());
	}

//	@Then("Admin receives {int} Method Not Allowed")
//	public void admin_receives_method_not_allowed(Integer int1) {
//	   
//	   
//	}

	@Given("Admin creates GET request with invalid content type")
	public void admin_creates_get_request_with_invalid_content_type() {
		TestcaseWrapper wrapper = getTestData();

    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
		String token = ScenarioContext.get("token", String.class);

        request = given()
                .header("Authorization", "Bearer " + token)   // keep token
                .header("Content-Type", "text/plain");   
	}
	
//------------------------------------Get batch by batchId---------------------------------------------
	
	@Given("Admin creates GET request with valid Batch ID")
	public void admin_creates_get_request_with_valid_batch_id() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
    	 String batchId = ScenarioContext.get("createdBatchId", String.class);
    	 String endpoint = "/batches/batchId/" + batchId;
    	 ScenarioContext.set("endpoint", endpoint);
	}
	
	@When("Admin sends HTTPS request to the endpoint for batch with batchId")
	public void admin_sends_https_request_to_the_endpoint_for_batch_with_batch_id() {
	   
		String endpoint = ScenarioContext.get("endpoint", String.class);
		response = request.when().get(endpoint);
		System.out.println("Reponse Body:\n" +response.getBody().asString());
	}

	@Given("Admin creates GET request with inactive Batch ID")
	public void admin_creates_get_request_with_inactive_batch_id() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
    	String inactiveBatchId = ScenarioContext.get("inactiveBatchId", String.class);
        String endpoint = "/batches/batchId/" + inactiveBatchId;
        ScenarioContext.set("endpoint", endpoint);  
	}

	@Given("Admin creates GET request with invalid Batch ID")
	public void admin_creates_get_request_with_invalid_batch_id() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
    	String invalidBatchId = "1234";
        String endpoint = "/batches/batchId/" + invalidBatchId;
        ScenarioContext.set("endpoint", endpoint);  
	    
	}
	
	@Given("Admin creates GET request with invalid endpoint")
	public void admin_creates_get_request_with_invalid_endpoint() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
		
	}
	
	@When("Admin sends HTTPS request to the invalid endpoint for get request")
	public void admin_sends_https_request_to_the_invalid_endpoint_for_get_request() {
	   
		response = request.when().get(testData.getEndpoint());
	}

	@Given("Admin creates POST request with valid endpoint")
	public void admin_creates_post_request_with_valid_endpoint() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);  
	}
	
	@When("Admin sends POST HTTPS request to the endpoint for batch by batch id")
	public void admin_sends_post_https_request_to_the_endpoint_for_batch_by_batch_id() {
	    
		response = request.when().post(testData.getEndpoint());
	}
	
	@Given("Admin creates GET request with invalid content type batch by batch id")
	public void admin_creates_get_request_with_invalid_content_type_batch_by_batch_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);  
    	String token = ScenarioContext.get("token", String.class);

        request = given()
                .header("Authorization", "Bearer " + token)  
                .header("Content-Type", "text/plain");
	}

	@When("Admin sends HTTPS request to the endpoint for invalid content method")
	public void admin_sends_https_request_to_the_endpoint_for_invalid_content_method() {
	    
		response = request.when().get(testData.getEndpoint());
	}
	
//----------------------------------------Get batch by batchName-------------------------------------------------
	
	@Given("Admin creates GET request with valid Batch name")
	public void admin_creates_get_request_with_valid_batch_name() {
	   
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
    	 String batchName = ScenarioContext.get("createdBatchName", String.class);
    	 String endpoint = "/batches/batchName/" + batchName;
    	 ScenarioContext.set("endpoint", endpoint);
		
	}
	
	@When("Admin sends HTTPS request to the endpoint for batch by batchname")
	public void admin_sends_https_request_to_the_endpoint_for_batch_by_batchname() {
	    
		String endpoint = ScenarioContext.get("endpoint", String.class);
		response = request.when().get(endpoint);
		System.out.println("Reponse Body:\n" +response.getBody().asString());
	}
	
	@Given("Admin creates GET request with invalid Batch Name")
	public void admin_creates_get_request_with_invalid_batch_name() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
    	String invalidBatchName = "Javascript-01";
        String endpoint = "/batches/batchName/" + invalidBatchName;
        ScenarioContext.set("endpoint", endpoint);
	}
	@When("Admin sends POST HTTPS request to the endpoint for batch by batchName")
	public void admin_sends_post_https_request_to_the_endpoint_for_batch_by_batch_name() {
	    
		response = request.when().post(testData.getEndpoint());
	}
	
//-------------------------------------Get Batch by programId---------------------------------------------------
	
	@Given("Admin creates GET request with valid Program Id")
	public void admin_creates_get_request_with_valid_program_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
		int programId = ScenarioContext.get("programId", Integer.class);
		String endpoint = "/batches/program/" + programId;
   	    ScenarioContext.set("endpoint", endpoint); 
	}

	@When("Admin sends HTTPS request to the endpoint for batch with programId")
	public void admin_sends_https_request_to_the_endpoint_for_batch_with_program_id() {
	    
		String endpoint = ScenarioContext.get("endpoint", String.class);
		response = request.when().get(endpoint);
		System.out.println("Reponse Body:\n" +response.getBody().asString());
	}

	@Given("Admin creates GET request with invalid Program Id")
	public void admin_creates_get_request_with_invalid_program_id() {
	    
		TestcaseWrapper wrapper = getTestData();
    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getGetRequest()
    	);
    	int invalidProgramId = 1234;
        String endpoint = "/batches/batchName/" + invalidProgramId;
        ScenarioContext.set("endpoint", endpoint);
	}

	@When("Admin sends POST HTTPS request to the endpoint for batch with programId")
	public void admin_sends_post_https_request_to_the_endpoint_for_batch_with_program_id() {
	    
		response = request.when().post(testData.getEndpoint());  
	}
	
//-------------------------------------Update by BatchId---------------------------------------------------------
	
	@Given("Admin creates PUT request with valid BatchId")
	public void admin_creates_put_request_with_valid_batch_id() {
	    
		TestcaseWrapper wrapper = getTestData();
		testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
		        wrapper.getTests()
		);
		batchData = testData.getBatchData();
		int programId = ScenarioContext.get("programId", Integer.class);
	    String programName1 = ScenarioContext.get("programName", String.class);
	    
	    batchData.setProgramId(programId);
	    batchData.setProgramName(programName1);
	    System.out.println(programName1 + ", programid:" + programId);
	    String randomSuffix = RandomLetters.randomNumber(5);
		batchData.setBatchName(programName1 + "_" + randomSuffix);
    	String batchId = ScenarioContext.get("createdBatchId", String.class);
   	 	String endpoint = "/batches/" + batchId;
   	 	ScenarioContext.set("endpoint", endpoint);
   	 	request.contentType(testData.getContentType())
		.body(batchData);
    	
	}

	@When("Admin sends HTTPS request to the endpoint for put request")
	public void admin_sends_https_request_to_the_endpoint_for_put_request() {
	    
		String endpoint = ScenarioContext.get("endpoint", String.class);
		response = request.when().put(endpoint);
		System.out.println("Reponse Body:\n" +response.getBody().asString());
	}

	@Then("Admin receives {int} OK Status with updated value in response body.")
	public void admin_receives_ok_status_with_updated_value_in_response_body(Integer statusCode) {
	    
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
    	int status = response.getStatusCode();
    	System.out.println("Status: " + status);
  
	}

	@Given("Admin creates PUT request with invalid BatchId")
	public void admin_creates_put_request_with_invalid_batch_id() {
	    
	    
	}

	@Then("Admin receives {int} Not Found Status with error message")
	public void admin_receives_not_found_status_with_error_message(Integer int1) {
	    
	    
	}

	@Given("Admin creates PUT request with missing mandatory fields")
	public void admin_creates_put_request_with_missing_mandatory_fields() {
	    
	    
	}

	@Then("Admin receives {int} Bad Request Status with error message")
	public void admin_receives_bad_request_status_with_error_message(Integer int1) {
	    
	    
	}

	@Given("Admin creates PUT request with batch name that is already exist in the system")
	public void admin_creates_put_request_with_batch_name_that_is_already_exist_in_the_system() {
	    
	    
	}

	@Then("Admin receives {int} Bad Request with error message")
	public void admin_receives_bad_request_with_error_message(Integer int1) {
	    
	    
	}

	@Given("Admin creates PUT request with invalid batch name format")
	public void admin_creates_put_request_with_invalid_batch_name_format() {
	    
	    
	}

	@Given("Admin creates PUT request with batch name length more than {int} characters including prefixed program name")
	public void admin_creates_put_request_with_batch_name_length_more_than_characters_including_prefixed_program_name(Integer int1) {
	    
	    
	}

	@Given("Admin creates PUT request with batch name length less than {int} characters including prefixed program name")
	public void admin_creates_put_request_with_batch_name_length_less_than_characters_including_prefixed_program_name(Integer int1) {
	    
	    
	}

	@Given("Admin creates a PUT request with invalid batch description")
	public void admin_creates_a_put_request_with_invalid_batch_description() {
	    
	    
	}

	@Given("Admin creates a PUT request with invalid batch status")
	public void admin_creates_a_put_request_with_invalid_batch_status() {
	    
	    
	}

	@Given("Admin creates a PUT request with invalid batch number of classes")
	public void admin_creates_a_put_request_with_invalid_batch_number_of_classes() {
	    
	    
	}

	@Given("Admin creates a PUT request with invalid program id")
	public void admin_creates_a_put_request_with_invalid_program_id() {
	    
	    
	}

	@Given("Admin creates a PUT request with special characters in program name")
	public void admin_creates_a_put_request_with_special_characters_in_program_name() {
	    
	    
	}

	@Given("Admin creates a PUT request with numbers in program name")
	public void admin_creates_a_put_request_with_numbers_in_program_name() {
	    
	    
	}

	@Given("Admin creates a PUT request with program name that does not match the associated program id")
	public void admin_creates_a_put_request_with_program_name_that_does_not_match_the_associated_program_id() {
	    
	    
	}

	@Then("Admin receives a {int} OK status and the response body contains the program details corresponding to the provided program id")
	public void admin_receives_a_ok_status_and_the_response_body_contains_the_program_details_corresponding_to_the_provided_program_id(Integer int1) {
	    
	    
	}

	@Given("Admin creates PUT request with inactive program")
	public void admin_creates_put_request_with_inactive_program() {
	    
	    
	}

	@Given("Admin creates PUT request with valid request body")
	public void admin_creates_put_request_with_valid_request_body() {
	    
	    
	}

	@Then("Admin receives {int} not found")
	public void admin_receives_not_found(Integer int1) {
	    
	    
	}

	@Given("Admin creates PUT request with invalid content type")
	public void admin_creates_put_request_with_invalid_content_type() {
	    
	    
	}

	
//-----------------------------------Delete batch by batchID--------------------------------------------------
	
	@Given("Admin creates DELETE request with valid BatchId")
	public void admin_creates_delete_request_with_valid_batch_id() {
	   
		TestcaseWrapper wrapper = getTestData();

    	testData = JsonReader.getTestDataByScenarioName(
		        Hooks.scenario.getName(),
    	        wrapper.getDeleteRequest()
    	);
    	 String batchId = ScenarioContext.get("createdBatchId", String.class);
    	 String endpoint = "/batches/" + batchId;
    	 ScenarioContext.set("endpoint", endpoint);
//    	 request = given()
//    	           .header("Content-Type", "application/json");
	}
	
	@When("Admin sends HTTPS request to the endpoint for deleting batch with batchId")
	public void admin_sends_https_request_to_the_endpoint_for_deleting_batch_with_batch_id() {
	    
		String endpoint = ScenarioContext.get("endpoint", String.class);
		response = request.when().delete(endpoint);
//		String inactiveBatchId = response.jsonPath().getString("batchId");
		ScenarioContext.set("inactiveBatchId", ScenarioContext.get("createdBatchId", String.class));
//		System.out.println("Deleted batchId: " + batchId);
//		System.out.println("Reponse Body:\n" +response.getBody().asString());
	}

	@Then("Admin receives {int} Ok status with message")
	public void admin_receives_ok_status_with_message(Integer statusCode) {
	   
		log.info(response.asPrettyString());
    	assertEquals(response.getStatusCode(), statusCode.intValue());
//    	int status = response.getStatusCode();
//    	String error = response.jsonPath().getString("error");
//
//    	System.out.println("Status: " + status);
//    	System.out.println("Error: " + error);
	}

}
