package pojoclass;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResetPasswordRequest {
	
	  @JsonProperty("userLoginEmailId")
	    private String userLoginEmailId;

	    @JsonProperty("password")
	    private String password;

	  
	    public String getUserLoginEmailId() {
	        return userLoginEmailId;
	    }

	    public void setUserLoginEmailId(String userLoginEmailId) {
	        this.userLoginEmailId = userLoginEmailId;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

}
