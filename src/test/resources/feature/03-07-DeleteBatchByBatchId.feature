@authToken
Feature: Delete Batch by BatchId [Delete Operation]

Background:  
Given Admin sets Authorization to Bearer Token for batch

@GetBatchWithValidBatchId @DeleteByBatchId
Scenario: Admin deletes a batch with valid batch id
Given Admin creates DELETE request with valid BatchId
When Admin sends HTTPS request to the endpoint for deleting batch with batchId
Then Admin receives 200 Ok status with message

@DeleteWithInbvalidBatchId
Scenario: Admin deletes a batch with invalid batch id
Given Admin creates DELETE request with invalid BatchId
When Admin sends HTTPS request to the endpoint for delete
Then Admin receives 404 Not Found Status with error message

@DeleteWithInvalidEndpoint
Scenario: Admin deletes a batch by batch id with invalid endpoint
Given Admin creates DELETE request with valid BatchId for invalid endpoint
When Admin sends HTTPS request to the invalid endpoint for delete
Then Admin receives 404 Not Found Status with error message

@DeleteByInvalidMethod
Scenario: Admin deletes a batch by batch id with invalid method
Given Admin creates POST request with valid BatchId
When Admin sends a POST HTTPS request to the valid endpoint
Then Admin receives 405 method not allowed

@DeleteWtihInvalidContentType
Scenario: Admin deletes a batch by batch id with invalid content type
Given Admin creates DELETE request with invalid content type
When Admin sends HTTPS request to the valid endpoint
Then Admin receives 415 unsupported media type

