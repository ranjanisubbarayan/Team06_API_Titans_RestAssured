Feature: Logout API

Background:  Admin sets bearer token for logout API

@authToken @LogoutAPI
  Scenario Outline: Validate Logout API scenarios
    Given Admin creates GET logout request for "<scenarioName>" in logout API
    When Admin sends logout request for "<scenarioName>" in logout API
    Then Admin validates logout response for "<scenarioName>" in logout API

    Examples:
      |scenarioName|
      |Logout Successfully|
      |Logout With Invalid Endpoint|
      |Logout With Invalid Method|
      |Logout with No Auth|
      |Logout With Invalid Token|
      |Logout With Expired Token|