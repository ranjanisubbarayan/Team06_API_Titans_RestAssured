@authToken
Feature: GET All Batches[Get Operation]

Background:  
Given Admin sets Authorization to Bearer Token for batch 

Scenario: Admin retrieves all batches with valid endpoint
Given Admin creates GET request 
When Admin sends HTTPS request to the endpoint for get request
Then Admin receives 200 OK Status with response body.                                                                

Scenario: Admin retrieves all batches with invalid endpoint
Given Admin creates GET request 
When Admin sends HTTPS request to the invalid endpoint for get
Then Admin receives 404 Not Found with error message

Scenario: Admin retrieves all batches with invalid method
Given Admin creates POST request 
When Admin sends POST HTTPS request to the endpoint 
Then Admin receives 405 method not allowed 

Scenario: Admin retrieves all batches with invalid content type
Given Admin creates GET request with invalid content type
When Admin sends HTTPS request to the endpoint for get request
Then Admin receives 415 unsupported media type
