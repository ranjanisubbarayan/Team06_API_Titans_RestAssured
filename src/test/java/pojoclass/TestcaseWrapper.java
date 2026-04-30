package pojoclass;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestcaseWrapper {

    @JsonProperty("PostRequest")
    private List<JsonTestData> postRequest;

    @JsonProperty("GetRequest")
    private List<JsonTestData> getRequest;
    
    @JsonProperty("DeleteRequest")
    private List<JsonTestData> deleteRequest;

 
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
    
    public List<JsonTestData> getDeleteRequest() {
        return deleteRequest;
    }

    public void setDeleteRequest(List<JsonTestData> deleteRequest) {
        this.deleteRequest = deleteRequest;
    }
}
