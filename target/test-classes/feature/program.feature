@ProgramModule
Feature: Program Module API - Add New Program

Background:
    Given Admin sets Authorization to Bearer Token
 
 @authToken @getallprogram   
    
   Scenario: Admin retrieves all programs with valid endpoint
    Given  Admin creates GET Request for the LMS Program API
    When Admin sends a HTTPS GET request to the valid all programs endpoint in program API
    Then Admin receives Status with response body in GET program API
    Then Admin Validates response body matches JSON schema in  in GET program API

@authToken @postprogram
  Scenario: Admin creates a program with valid request body and authorization
    Given Admin creates POST Request with valid request body in program API
    When Admin sends a HTTPS request to the valid endpoint in program API
    Then Admin receives 201 Created Status with response body in program API
     Then Admin Validates response body matches JSON schema in  in POST program API

    
 @authToken @deleteprogram   
  Scenario: Validate Delete Program By ProgramID
    Given  Admin creates DELETE Request with valid program ID in endpoints in program API
    When  Admin sends a HTTPS DELETE request to the valid endpoint in program API
    Then Admin receives Status with response body in DELETE program API