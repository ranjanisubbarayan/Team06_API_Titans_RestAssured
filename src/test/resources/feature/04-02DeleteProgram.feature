@DeleteProgramAPI
 
 Feature: Delete Program API 
 
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
    
 @authToken @deleteprogram   
  Scenario: Validate Delete Program By ProgramID
    Given  Admin creates DELETE Request with valid program ID in endpoints in program API
    When  Admin sends a HTTPS DELETE request to the valid endpoint in program API
    Then Admin receives Status with response body in DELETE program API
        

 @authToken @deleteprogram_byprogramID
  Scenario Outline: Validate Delete Program By ProgramID API scenarios
    Given Admin creates delete request with "<scenarioName>" for the delete program API
    When Admin sends DELETE request for "<endpoint>" for the delete program API
    Then Admin validates response for "<Scenariotype>" for the delete program API

  Examples:
   
   |scenarioName | endpoint|Scenariotype| 
   | valid program ID chaining | valid endpoint| Delete Program ByID chaining|
   |invalid alphabets in program ID endpoints | valid endpoint | Delete Program With Alphabet ID| 
   |invalid endpoint |  invalid endpoint|Delete Program With Invalid Endpoint| 
   |HTTPS request invalid method| valid endpoint| Delete Program With Invalid Method|