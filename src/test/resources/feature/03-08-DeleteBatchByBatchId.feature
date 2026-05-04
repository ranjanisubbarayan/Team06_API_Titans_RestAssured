#@authToken
#Feature: Delete Batch by BatchId [Delete Operation]
#
#Background:  
#Given Admin sets Authorization to Bearer Token for batch
#
#Scenario: Admin deletes a batch with valid batch id
#Given Admin creates DELETE request with valid BatchId
#When Admin sends HTTPS request to the endpoint for deleting batch with batchId
#Then Admin receives 200 Ok status with message