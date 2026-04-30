Feature: Add New Batch [Post Operation]

Rule: Admin adds new batch with Authorized bearer token

Background: Admin sets Authorization to Bearer Token. 
Given Admin creates POST request with valid credentials
When Admin sends a HTTPS POST request to the valid login endpoint

Scenario: Admin creates batch with both mandatory and optional fields
Given Admin creates POST request with mandatory and optional fields
When Admin sends HTTPS request to the endpoint 
Then Admin receives 201 Created status with response body.                                                          

Scenario: Admin creates batch with a program name that does not match the associated program id
Given Admin creates a POST request with program name that does not match the associated program id
When Admin sends HTTPS request to the endpoint 
Then Admin receives a 201 created and the response body contains the program details corresponding to the provided program id  

Scenario: Admin creates batch with only optional fields 
Given Admin creates POST request with only optional fields in request body 
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with only mandatory fields
Given Admin creates POST request with only mandatory fields in request body 
When Admin sends HTTPS request to the endpoint 
Then Admin receives 201 Created status with response body.                                                          

Scenario: Admin creates batch without underscore format in batch name
Given Admin creates POST request without underscore in batch name 
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with hyphen format in batch name
Given Admin creates POST request with hyphen in batch name
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with characters in the suffix of batch name
Given Admin creates POST request with characters in the suffix of batch name
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with special characters in the suffix of batch name
Given Admin creates POST request with special characters in the suffix of batch name
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with batch name length more than 28 characters in total
Given Admin creates POST request with batch name length more than 28 characters including prefixed program name
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with batch name length less than 6 characters in total
Given Admin creates POST request with batch name length less than 6 characters including prefixed program name
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with duplicate batch name
Given Admin creates POST request with batch name that is already existing in the system
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with batch description less than 4 characters
Given Admin creates POST request with batch description less than 4 characters
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with batch description more than 25 characters
Given Admin creates POST request with batch description more than 25 characters
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with random characters in status field
Given Admin creates POST request with random characters in status field
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with random numbers in status field
Given Admin creates POST request with random numbers in status field
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with special characters in status field
Given Admin creates POST request with special characters in status field
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with non numeric value in number of classes
Given Admin creates POST request with non numeric in number of classes field
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with number of classes length less than 1
Given Admin creates POST request with number of classes length less than 1
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with number of classes length more than 99
Given Admin creates POST request with with number of classes length more than 99
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with inactive program ID
Given Admin creates POST request with inactive program id
When Admin sends HTTPS request to the endpoint 
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin creates batch with program that is not exist in the system
Given Admin creates POST request with program id that is not exist in the system
When Admin sends HTTPS request to the endpoint 
Then Admin receives 404 not found with valid error message

Scenario: Admin creates batch with invalid endpoint
Given Admin creates POST request with valid request body
When Admin sends HTTPS request to the invalid endpoint 
Then Admin receives 404 not found with valid error message

Scenario: Admin creates batch with invalid content type
Given Admin creates POST request with invalid content type
When Admin sends a HTTPS request to the valid endpoint
Then Admin receives 415 unsupported media type

Scenario: Admin creates batch with invalid method
Given Admin creates GET request with valid request body
When Admin sends GET HTTPS request to the valid endpoint
Then Admin receives 405 method not allowed

Rule: Admin adds new batch with No Auth

Background: Admin sets Authorization to No Auth.
Given Admin creates POST request with No Auth
When Admin sends a HTTPS POST request to the valid login endpoint

Scenario: Admin creates a batch with without authorization.
Given Admin creates POST request without authorization
When Admin sends HTTPS request to the endpoint 
Then Admin receives 401 Unauthorized

                       