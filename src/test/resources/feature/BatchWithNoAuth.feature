Feature: Add New Batch with NoAuth [Post Operation]

Background: 
Given Admin creates POST request with No Auth
When Admin sends a HTTPS POST request to the valid login endpoint

Scenario: Admin creates a batch with without authorization.
Given Admin creates POST request without authorization
When Admin sends HTTPS request to the endpoint with no auth
Then Admin receives 401 Unauthorized