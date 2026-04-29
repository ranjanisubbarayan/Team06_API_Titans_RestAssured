package pojoclass;

import java.util.List;

public class LoginResponse {

    private String token;
    private String type;
    private String userId;
    private String email;
    private List<String> roles;
    private String status;
    private boolean passwordExpired;

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getStatus() {
        return status;
    }

    public boolean isPasswordExpired() {
        return passwordExpired;
    }
}