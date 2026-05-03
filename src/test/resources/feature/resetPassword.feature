Feature: Reset Password API

Background: Admin sets Authorization to Bearer Token for reset password API

 @login_ValidCredential 
 
  Scenario: Admin generates token with valid credential for the login API
    Given Admin creates POST request with valid credentials for the login API
    When Admin sends a HTTPS POST request to the valid login endpoint for the login API
    Then Admin receives 200 Created with auto generated token for the login API
    
    
 @ResetPasswordAPI   
  Scenario Outline: Validate Reset Password API scenarios
    Given Admin creates POST request for "<scenarioName>" reset password API
    When Admin sends POST request for "<scenarioName>" reset password API
    Then Admin validates POST response for "<scenarioName>" reset password API

    Examples:
      |scenarioName|
      |Reset Password With Invalid Email|
      |Reset Password With Invalid Password|
      |Reset Password With Invalid Endpoint|
      |Reset Password With Invalid Content Type|
      |Reset Password With Invalid Method|
      |Reset Password Without Authentication|
      |Reset Password With Expired Token|
      |Reset Password With Empty Token|
   