 @DeleteProgramAPI
 
 Feature: Delete Program API 
    
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