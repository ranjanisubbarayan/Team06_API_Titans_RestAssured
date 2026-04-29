package pojoclass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonTestData {

    @JsonProperty("testcaseName")
    public String testcaseName;

    @JsonProperty("endpoint")
    public String endpoint;

    @JsonProperty("Statusmessage") 
    public String statusmessage;

    @JsonProperty("contentType")
    public String contentType;
    
    @JsonProperty("loginRequest")
    public Login loginRequest;
    
    @JsonProperty("BatchData")
    public Data data;
    
    public Data getData() {
    	return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
    
    public String expectedType;
    public String expectedEmail;
    public String expectedStatus;
    
  
    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getStatusmessage() {
        return statusmessage;
    }

    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    
    public Login getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(Login loginRequest) {
        this.loginRequest = loginRequest;
    }
    
    public String getExpectedType() {
        return expectedType;
    }

    public String getExpectedEmail() {
        return expectedEmail;
    }

    public String getExpectedStatus() {
        return expectedStatus;
    }
    
   
}
