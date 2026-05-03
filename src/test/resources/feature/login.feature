Feature: User Sign In API

Background: Admin sets No Auth 

@login_ValidCredential

  Scenario: Admin generates token with valid credential for the login API
    Given Admin creates POST request with valid credentials for the login API
    When Admin sends a HTTPS POST request to the valid login endpoint for the login API
    Then Admin receives 200 Created with auto generated token for the login API
    
    
@NegativeLoginAPI    
  
Scenario Outline: Validate Negative login API scenarios
  Given Admin creates POST request for "<scenarioName>" for the login API
  When Admin sends request to the valid endpoint for the login API
  Then Admin receives the response for "<scenarioName>" for the login API

    Examples:
      |scenarioName|
      |Login with invalid content type|
      |Login with empty email|
      |Login with empty password|
      |Login without request body|
      |Login With Invalid Method|
      |Login With Invalid Base URL|
      |Login With Invalid Endpoint|
      |Login With Special Characters Email|
      |Login With Email Having Spaces|
      |Login With Null Email|
      |Login With Unregistered Email|
      |Login With Special Characters Password|
      |Login With Password Having Spaces|
      |Login With Null Password|
    
     
     
     
@ForgotpasswordAPI
  Scenario Outline: Validate Forgot Password Confirm email LoginAPI scenarios
    Given Admin creates forgot password POST request with "<scenarioName>"
    When Admin sends a HTTPS request to the valid endpoint for forgot password API with "<scenarioName>"
    Then Admin validates forgot password response with "<scenarioName>"
    Then Admin Validates response body matches JSON schema in forgot password API

    Examples:
      | scenarioName|
      |Valid Email in forgot password API|
      |Invalid Content Type in forgot password API|
      |Invalid Method in forgot password API|
      |Invalid Endpoint in forgot password API|
      |Empty Email in forgot password API|
      |Invalid Email in forgot password API|
      |Null Email in forgot password API|
      |Unregistered Email in forgot password API|