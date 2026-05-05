@Addnewbatch 
Feature: Add New Batch [Post Operation]

Background:  
Given Admin sets Authorization to Bearer Token for batch


@PostBatch-01
Scenario: Admin creates batch with both mandatory and optional fields
Given Admin creates POST request with mandatory and optional fields
When Admin sends HTTPS request to the endpoint 
Then Admin receives 201 Created status with response body.                                                          

#@postprogram @PostBatch-02
#Scenario: Admin creates batch with a program name that does not match the associated program id
#Given Admin creates a POST request with program name that does not match the associated program id
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives a 201 created and the response body contains the program details corresponding to the provided program id  
#
#@PostBatch-03
#Scenario: Admin creates batch with only optional fields 
#Given Admin creates POST request with only optional fields in request body 
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives 400 Bad Request with valid error message
#
#@postprogram @PostBatch-04
#Scenario: Admin creates batch with only mandatory fields
#Given Admin creates POST request with only mandatory fields in request body 
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives 201 Created status with response body.                                                          
#
#@PostBatch-05
#Scenario Outline: Admin creates batch with invalid batch name formats 
#Given Admin creates POST request with "<testcaseName>"
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives 400 Bad Request with valid error message
#
#Examples:
#|testcaseName |
#|without underscore in batch name|
#|with hyphen in batch name|
#|with characters in the suffix of batch name|
#|with special characters in the suffix of batch name|
#|with batch name length more than 28 characters|
#|with batch name length less than 6 characters|
#|with duplicate batch name|
#
#@PostBatch-06
#Scenario Outline: Admin creates batch with invalid batch description
#Given Admin creates POST request with "<testcaseName>" for batch description
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives 400 Bad Request with valid error message
#Examples:
#|testcaseName |
#|with batch description less than 4 characters|
#|with batch description more than 25 characters|
#
#Scenario Outline: Admin creates batch with invalid status field
#Given Admin creates POST request with "<testcaseName>" in status field
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives 400 Bad Request with valid error message
#Examples:
#|testcaseName |
#|with random characters in status field|
#|with random numbers in status field|
#|with special characters in status field|
#
#Scenario Outline: Admin creates batch with invalid number of classes
#Given Admin creates POST request with "<testcaseName>" in number of classes field
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives 400 Bad Request with valid error message
#Examples:
#|testcaseName |
#|with non numeric in number of classes|
#|with number of classes length less than 1|
#|with number of classes length more than 99|
#
#Scenario: Admin creates batch with inactive program ID
#Given Admin creates POST request with inactive program id
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives 400 Bad Request with valid error message
#
#Scenario: Admin creates batch with program that is not exist in the system
#Given Admin creates POST request with program id that is not exist in the system
#When Admin sends HTTPS request to the endpoint 
#Then Admin receives 404 not found with valid error message
#
#Scenario: Admin creates batch with invalid endpoint
#Given Admin creates POST request with valid request body
#When Admin sends HTTPS request to the invalid endpoint 
#Then Admin receives 404 not found with valid error message
#
#Scenario: Admin creates batch with invalid content type
#Given Admin creates POST request with invalid content type
#When Admin sends a HTTPS request to the valid endpoint for batch
#Then Admin receives 415 unsupported media type
#
#Scenario: Admin creates batch with invalid method
#Given Admin creates GET request with valid request body
#When Admin sends GET HTTPS request to the valid endpoint
#Then Admin receives 405 method not allowed
                    