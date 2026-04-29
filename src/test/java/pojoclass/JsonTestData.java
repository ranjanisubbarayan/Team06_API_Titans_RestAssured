package pojoclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class JsonTestData {

    @JsonProperty("testcaseName")
    private String testcaseName;

    @JsonProperty("endpoint")
    private String endpoint;

    @JsonProperty("Statusmessage") 
    private String statusmessage;

    @JsonProperty("contentType")
    private String contentType;
    
    @JsonProperty("loginRequest")
    private Login loginRequest;
    
    @JsonProperty("programRequest")
    private ProgramRequest programRequest;
    
    private String expectedType;
    private String expectedEmail;
    private String expectedStatus;
    
    @JsonProperty("expectedStatusCode")
    private Integer expectedStatusCode;
  
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
    
    public ProgramRequest getProgramRequest() {
        return programRequest;
}
    public Integer getExpectedStatusCode() {
        return expectedStatusCode;
    }
    
}
