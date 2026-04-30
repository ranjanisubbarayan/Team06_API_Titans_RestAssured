package pojoclass;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)

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
    
    @JsonProperty("programRequest")
    private ProgramRequest programRequest;
    
    @JsonProperty("expectedType")
    private String expectedType;
    
    @JsonProperty("expectedEmail")
    private String expectedEmail;
    
    @JsonProperty("expectedStatus")
    private String expectedStatus;
    
    @JsonProperty("rawBody")
    private String rawBody;
    
    @JsonProperty("expectedStatusCode")
    private Integer expectedStatusCode;
    
    @JsonProperty("programId")
    private Integer programId;

    @JsonProperty("expectedMessage")
    private String expectedMessage;

  
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
    
    public String getRawBody() {
        return rawBody;
    }

    public void setRawBody(String rawBody) {
        this.rawBody = rawBody;
    }
    
    public Integer getProgramId() {
        return programId;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }
    
}
