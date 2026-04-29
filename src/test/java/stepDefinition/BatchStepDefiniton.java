package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BatchStepDefiniton {
	
	@Given("Admin creates POST request with mandatory and optional fields")
	public void admin_creates_post_request_with_mandatory_and_optional_fields() {
	    
	    
	}

	@When("Admin sends HTTPS request to the endpoint")
	public void admin_sends_https_request_to_the_endpoint() {
	    
	    
	}

	@Then("Admin receives {int} Created status with response body.")
	public void admin_receives_created_status_with_response_body(Integer int1) {
	    
	    
	}

	@Given("Admin creates a POST request with program name that does not match the associated program id")
	public void admin_creates_a_post_request_with_program_name_that_does_not_match_the_associated_program_id() {
	    
	    
	}

	@Then("Admin receives a {int} created and the response body contains the program details corresponding to the provided program id")
	public void admin_receives_a_created_and_the_response_body_contains_the_program_details_corresponding_to_the_provided_program_id(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with only optional fields in request body")
	public void admin_creates_post_request_with_only_optional_fields_in_request_body() {
	    
	    
	}

	@Then("Admin receives {int} Bad Request with valid error message")
	public void admin_receives_bad_request_with_valid_error_message(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with only mandatory fields in request body")
	public void admin_creates_post_request_with_only_mandatory_fields_in_request_body() {
	    
	    
	}

	@Given("Admin creates POST request without underscore in batch name")
	public void admin_creates_post_request_without_underscore_in_batch_name() {
	    
	    
	}

	@Given("Admin creates POST request with hyphen in batch name")
	public void admin_creates_post_request_with_hyphen_in_batch_name() {
	    
	    
	}

	@Given("Admin creates POST request with characters in the suffix of batch name")
	public void admin_creates_post_request_with_characters_in_the_suffix_of_batch_name() {
	    
	    
	}

	@Given("Admin creates POST request with special characters in the suffix of batch name")
	public void admin_creates_post_request_with_special_characters_in_the_suffix_of_batch_name() {
	    
	    
	}

	@Given("Admin creates POST request with batch name length more than {int} characters including prefixed program name")
	public void admin_creates_post_request_with_batch_name_length_more_than_characters_including_prefixed_program_name(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with batch name length less than {int} characters including prefixed program name")
	public void admin_creates_post_request_with_batch_name_length_less_than_characters_including_prefixed_program_name(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with batch name that is already existing in the system")
	public void admin_creates_post_request_with_batch_name_that_is_already_existing_in_the_system() {
	    
	    
	}

	@Given("Admin creates POST request with batch description less than {int} characters")
	public void admin_creates_post_request_with_batch_description_less_than_characters(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with batch description more than {int} characters")
	public void admin_creates_post_request_with_batch_description_more_than_characters(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with random characters in status field")
	public void admin_creates_post_request_with_random_characters_in_status_field() {
	    
	    
	}

	@Given("Admin creates POST request with random numbers in status field")
	public void admin_creates_post_request_with_random_numbers_in_status_field() {
	    
	    
	}

	@Given("Admin creates POST request with special characters in status field")
	public void admin_creates_post_request_with_special_characters_in_status_field() {
	    
	    
	}

	@Given("Admin creates POST request with non numeric in number of classes field")
	public void admin_creates_post_request_with_non_numeric_in_number_of_classes_field() {
	    
	    
	}

	@Given("Admin creates POST request with number of classes length less than {int}")
	public void admin_creates_post_request_with_number_of_classes_length_less_than(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with with number of classes length more than {int}")
	public void admin_creates_post_request_with_with_number_of_classes_length_more_than(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with inactive program id")
	public void admin_creates_post_request_with_inactive_program_id() {
	    
	    
	}

	@Given("Admin creates POST request with program id that is not exist in the system")
	public void admin_creates_post_request_with_program_id_that_is_not_exist_in_the_system() {
	    
	    
	}

	@Then("Admin receives {int} not found with valid error message")
	public void admin_receives_not_found_with_valid_error_message(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with valid request body")
	public void admin_creates_post_request_with_valid_request_body() {
	    
	    
	}

	@When("Admin sends HTTPS request to the invalid endpoint")
	public void admin_sends_https_request_to_the_invalid_endpoint() {
	    
	    
	}

	@Given("Admin creates POST request with invalid content type")
	public void admin_creates_post_request_with_invalid_content_type() {
	    
	    
	}

	@When("Admin sends a HTTPS request to the valid endpoint")
	public void admin_sends_a_https_request_to_the_valid_endpoint() {
	    
	    
	}

	@Then("Admin receives {int} unsupported media type")
	public void admin_receives_unsupported_media_type(Integer int1) {
	    
	    
	}

	@Given("Admin creates GET request with valid request body")
	public void admin_creates_get_request_with_valid_request_body() {
	    
	    
	}

	@When("Admin sends GET HTTPS request to the valid endpoint")
	public void admin_sends_get_https_request_to_the_valid_endpoint() {
	    
	    
	}

	@Then("Admin receives {int} method not allowed")
	public void admin_receives_method_not_allowed(Integer int1) {
	    
	    
	}

	@Given("Admin creates POST request with No Auth")
	public void admin_creates_post_request_with_no_auth() {
	    
	    
	}

	@Given("Admin creates POST request without authorization")
	public void admin_creates_post_request_without_authorization() {
	    
	    
	}

	@Then("Admin receives {int} Unauthorized")
	public void admin_receives_unauthorized(Integer int1) {
	    
	    
	}

}
