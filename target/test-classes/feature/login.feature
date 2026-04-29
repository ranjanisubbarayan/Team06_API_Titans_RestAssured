Feature: User Sign In API

Background: Admin sets No Auth 

@postlogin

  Scenario: Admin generates token with valid credential
    Given Admin creates POST request with valid credentials
    When Admin sends a HTTPS POST request to the valid login endpoint
    Then Admin receives 200 Created with auto generated token