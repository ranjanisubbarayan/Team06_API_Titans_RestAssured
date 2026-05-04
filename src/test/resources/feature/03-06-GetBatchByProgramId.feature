@authToken
Feature: GET Batch by ProgramId[Get Operation]

Background:  
Given Admin sets Authorization to Bearer Token for batch 

#Scenario: Admin retrieves a batch with valid Program Id
#Given Admin creates GET request with valid Program Id
#When Admin sends HTTPS request to the endpoint for batch with programId
#Then Admin receives 200 OK Status with response body.                                                                  

#Scenario: Admin retrieves a batch with invalid Program Id
#Given Admin creates GET request with invalid Program Id
#When Admin sends HTTPS request to the endpoint for batch with programId
#Then Admin receives 404 Not Found with error message

#Scenario: Admin retrieves a batch by Program Id with invalid endpoint
#Given Admin creates GET request 
#When Admin sends HTTPS request to the invalid endpoint for get request
#Then Admin receives 404 Not Found with error message

#Scenario: Admin retrieves a batch by Program Id with invalid method
#Given Admin creates POST request 
#When Admin sends POST HTTPS request to the endpoint for batch with programId
#Then Admin receives 405 method not allowed

#Scenario: Admin retrieves a batch by Program Id with invalid content type
#Given Admin creates GET request with invalid content type
#When Admin sends HTTPS request to the endpoint for invalid content method  
#Then Admin receives 415 unsupported media type