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
    
//    @JsonProperty("BatchData")
//    private List<JsonTestData> BatchData;
    
//    @JsonProperty("BatchData")
//    public List<JsonTestData> batchData;
    
   
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
    
//    public void setTests(List<JsonTestData> tests) {
//        this.tests = tests;
//    }
//    
//    public List<JsonTestData> getTests() {
//        return tests;
//    }

//    public List<JsonTestData> getBatchData() {
//        return batchData;
//    }
//
//    public void setBatchData(List<JsonTestData> batchData) {
//        this.batchData = batchData;
//    }
    
  
}
