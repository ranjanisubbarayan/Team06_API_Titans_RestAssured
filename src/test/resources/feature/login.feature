Feature: User Sign In API

Background: Admin sets No Auth 

@postlogin_ValidCredential

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
      | scenarioName                         |
      | Login with invalid content type       |
      | Login with empty email                |
      | Login with empty password             |
      | Login without request body            |
     