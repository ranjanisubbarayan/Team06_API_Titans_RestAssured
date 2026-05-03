Feature: Get all Program without Auth
Background: Admin sets No Auth for the Program API scenario


@Getallprogram_withoutauth
  Scenario Outline: Validate Program GET API scenarios
    Given Admin creates GET request for "<scenarioName>" in program API
    When Admin sends GET request for "<scenarioName>" in program API
    Then Admin validates GET response for "<scenarioName>" in program API
       
 Examples:
      |scenarioName|
      |Get All Programs Without Auth|
      |Get Program By ID Without Auth|
      |Get All Programs With Users Without Auth|   