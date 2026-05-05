@updateprogramAPI
Feature: UpdateProgram Scenario

Background:
    Given Admin sets Authorization to Bearer Token

@authToken @postprogram
  Scenario: Admin creates a program with valid request body and authorization
    Given Admin creates POST Request with valid request body in program API
    When Admin sends a HTTPS request to the valid endpoint in program API
    Then Admin receives Created Status with response body in program API
     Then Admin Validates response body matches JSON schema in  in POST program API
     

@authToken @putprogrambyID
 Scenario Outline: Validate Update Program API scenarios
    Given Admin creates PUT request with "<scenarioName>" for update program API
    When Admin sends PUT request for "<scenarioName>" with valid endpoint for update program API
    Then Admin validates response for "<scenarioName>" for update program API
   Then Admin Validates response body matches JSON schema in  in Update program API
    Examples:
      | scenarioName| 
      |Valid Program ID Chaining| 
      |Invalid Program ID|
       |Without Request Body| 
      |Invalid Method| 
      | Invalid Endpoint| 
