@authToken
Feature: GET Batch by BatchId[Get Operation]

Background:  
Given Admin sets Authorization to Bearer Token for batch

@GetBatchWithValidBatchId
Scenario: Admin retrieves a batch with valid batch ID
Given Admin creates GET request with valid Batch ID
When Admin sends HTTPS request to the endpoint for batch with batchId
Then Admin receives 200 OK Status with response body.                                                                  

@DeleteByBatchId @GetInactiveBatchID
Scenario: Admin retrieves a batch with inactive batch ID
Given Admin creates GET request with inactive Batch ID
When Admin sends HTTPS request to the endpoint for batch with batchId
Then Admin receives 200 OK Status with response body.                                                                  

@GetInvalidBatchID
Scenario: Admin retrieves a batch with invalid batch ID
Given Admin creates GET request with invalid Batch ID
When Admin sends HTTPS request to the endpoint for batch with batchId 
Then Admin receives 404 Not Found with error message

@GetInvalidendpoint
Scenario: Admin retrieves a batch by batch id with invalid endpoint
Given Admin creates GET request with invalid endpoint
When Admin sends HTTPS request to the invalid endpoint for get request
Then Admin receives 404 Not Found with error message

@GetInvalidmethod
Scenario: Admin retrieves a batch by batch id with invalid method
Given Admin creates POST request with valid endpoint
When Admin sends POST HTTPS request to the endpoint for batch by batch id 
Then Admin receives 405 method not allowed

@GetInvalidContentType
Scenario: Admin retrieves a batch by batch id with invalid content type
Given Admin creates GET request with invalid content type batch by batch id
When Admin sends HTTPS request to the endpoint for invalid content method 
Then Admin receives 415 unsupported media type

