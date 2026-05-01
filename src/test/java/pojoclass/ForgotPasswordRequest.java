package pojoclass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordRequest {
	
	  @JsonProperty("userLoginEmailId")
	    private String userLoginEmailId;

	  
	    public String getUserLoginEmailId() {
	        return userLoginEmailId;
	    }

	    public void setUserLoginEmailId(String userLoginEmailId) {
	        this.userLoginEmailId = userLoginEmailId;
	    }

	
}
