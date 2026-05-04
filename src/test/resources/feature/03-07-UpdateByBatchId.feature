@authToken
Feature: Update by BatchId [Put Operation]

Background:  
Given Admin sets Authorization to Bearer Token for batch 

Scenario: Admin updates a batch with valid batch ID 
Given Admin creates PUT request with valid BatchId 
When Admin sends HTTPS request to the endpoint for put request
Then Admin receives 200 OK Status with updated value in response body. 

#Scenario: Admin updates a batch with invalid batch ID 
#Given Admin creates PUT request with invalid BatchId 
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 404 Not Found Status with error message 
#
#Scenario: Admin updates a batch by batch id with missing mandatory fields in request body
#Given Admin creates PUT request with missing mandatory fields
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request Status with error message
#
#Scenario: Admin updates a batch by batch id with duplicate batch name
#Given Admin creates PUT request with batch name that is already exist in the system  
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with error message
#
#Scenario: Admin updates a batch by batch id with invalid batch name format
#Given Admin creates PUT request with invalid batch name format   
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request Status with error message
#
#Scenario: Admin updates a batch by batch id with batch name more than allowable length
#Given Admin creates PUT request with batch name length more than 28 characters including prefixed program name
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with batch name less than allowable length in total
#Given Admin creates PUT request with batch name length less than 6 characters including prefixed program name
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with invalid batch description
#Given Admin creates a PUT request with invalid batch description
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with invalid batch status
#Given Admin creates a PUT request with invalid batch status
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with invalid batch number of classes
#Given Admin creates a PUT request with invalid batch number of classes
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with invalid program id
#Given Admin creates a PUT request with invalid program id
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with special characters in program name
#Given Admin creates a PUT request with special characters in program name
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with numbers in program name
#Given Admin creates a PUT request with numbers in program name
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with a program name that does not match the associated program id
#Given Admin creates a PUT request with program name that does not match the associated program id
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives a 200 OK status and the response body contains the program details corresponding to the provided program id                           
#
#Scenario: Admin updates a batch by batch id with inactive program
#Given Admin creates PUT request with inactive program
#When Admin sends HTTPS request to the endpoint for put request
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin updates a batch by batch id with invalid endpoint
#Given Admin creates PUT request with valid request body
#When Admin sends HTTPS request to the invalid endpoint 
#Then Admin receives 404 not found  
#
#Scenario: Admin updates a batch by batch id with invalid method
#Given Admin creates POST request with valid request body
#When Admin sends POST HTTPS request to the endpoint for invalid method 
#Then Admin receives 405 method not allowed
#
#Scenario: Admin updates a batch by batch id with invalid content type
#Given Admin creates PUT request with invalid content type
#When Admin sends HTTPS request to the endpoint for invalid content method  
#Then Admin receives 415 unsupported media type 
                                    