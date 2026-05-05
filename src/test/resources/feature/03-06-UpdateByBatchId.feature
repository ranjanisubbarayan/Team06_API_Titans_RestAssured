@authToken
Feature: Update by BatchId [Put Operation]

Background:  
Given Admin sets Authorization to Bearer Token for batch 

Scenario: Admin updates a batch with valid batch ID 
Given Admin creates PUT request with valid BatchId 
When Admin sends HTTPS request to the endpoint for put request
Then Admin receives 200 OK Status with updated value in response body. 

Scenario: Admin updates a batch with invalid batch ID 
Given Admin creates PUT request with invalid BatchId 
When Admin sends HTTPS request to the endpoint for put request
Then Admin receives 404 Not Found Status with error message 

Scenario: Admin updates a batch by batch id with missing mandatory fields in request body
Given Admin creates PUT request with missing mandatory fields
When Admin sends HTTPS request to the endpoint for put request
Then Admin receives 400 Bad Request Status with error message

Scenario Outline: Admin updates a batch by batch id with invalid batch details
Given Admin creates PUT request with "<testcaseName>"  
When Admin sends HTTPS request to the endpoint for put request
Then Admin receives 404 Not Found Status with error message

Examples:
|testcaseName |
|batch name that is already exist in the system|
|invalid batch name format|
|batch name length more than 28 characters|
|batch name length less than 6 characters|
|invalid batch description|
|invalid batch status|
|invalid batch number of classes|
|invalid program id|
|special characters in program name|
|numbers in program name|

Scenario: Admin updates a batch by batch id with a program name that does not match the associated program id
Given Admin creates a PUT request with program name that does not match the associated program id
When Admin sends HTTPS request to the endpoint for put request
Then Admin receives a 200 OK status and the response body contains the program details corresponding to the provided program id                           

Scenario: Admin updates a batch by batch id with inactive program
Given Admin creates PUT request with inactive program
When Admin sends HTTPS request to the endpoint for put request
Then Admin receives 400 Bad Request with valid error message

Scenario: Admin updates a batch by batch id with invalid endpoint
Given Admin creates PUT request with valid request body
When Admin sends HTTPS request to the invalid endpoint 
Then Admin receives 404 not found  

Scenario: Admin updates a batch by batch id with invalid method
Given Admin creates POST request with valid request body
When Admin sends POST HTTPS request to the endpoint for invalid method 
Then Admin receives 405 method not allowed

Scenario: Admin updates a batch by batch id with invalid content type
Given Admin creates PUT request with invalid content type
When Admin sends HTTPS request to the endpoint for invalid content type for put  
Then Admin receives 415 unsupported media type 
                                    