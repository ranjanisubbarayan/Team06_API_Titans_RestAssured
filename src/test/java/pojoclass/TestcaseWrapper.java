package pojoclass;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TestcaseWrapper {

    @JsonProperty("PostRequest")
    private List<JsonTestData> postRequest;

    @JsonProperty("GetRequest")
    private List<JsonTestData> getRequest;
    
    @JsonProperty("Tests")
    private List<JsonTestData> tests;
   
    @JsonProperty("DeleteRequest")
    private List<JsonTestData> deleteRequest;
    
    @JsonProperty("PutRequest")
    private List<JsonTestData> putRequest;

    @JsonProperty("ResetPasswordRequest")
    private List<JsonTestData> resetPasswordRequest;
 
    public List<JsonTestData> getPostRequest() {
        return postRequest;
    }
    
    

    public void setPostRequest(List<JsonTestData> postRequest) {
        this.postRequest = postRequest;
    }

    public List<JsonTestData> getGetRequest() {
        return getRequest;
    }

    public void setGetRequest(List<JsonTestData> getRequest) {
        this.getRequest = getRequest;
    }
    
    public List<JsonTestData> getTests() { return tests; }
    public void setTests(List<JsonTestData> tests) { this.tests = tests; }
  
    public List<JsonTestData> getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(List<JsonTestData> deleteRequest) {
        this.deleteRequest = deleteRequest;
    }
    
    public List<JsonTestData> getPutRequest() {
        return putRequest;
    }

    public void setPutRequest(List<JsonTestData> putRequest) {
        this.putRequest = putRequest;
    }
    
    public List<JsonTestData> getResetPasswordRequest() {
        return resetPasswordRequest;
    }

    public void setResetPasswordRequest(List<JsonTestData> resetPasswordRequest) {
        this.resetPasswordRequest = resetPasswordRequest;
    }
}
