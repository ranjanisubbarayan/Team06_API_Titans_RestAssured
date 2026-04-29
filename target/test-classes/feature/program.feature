@authToken
Feature: Program Module API - Add New Program

Background:
    Given Admin sets Authorization to Bearer Token
 
 @getallprogram   
    
   Scenario: Admin retrieves all programs with valid endpoint
    Given  Admin creates GET Request for the LMS API
    When Admin sends a HTTPS GET request to the valid all programs endpoint
    Then Admin receives 200 OK Status with response body

@postprogram
  Scenario: Admin creates a program with valid request body and authorization
    Given Admin creates POST Request with valid request body
    When Admin sends a HTTPS request to the valid endpoint
    Then Admin receives 201 Created Status with response body

