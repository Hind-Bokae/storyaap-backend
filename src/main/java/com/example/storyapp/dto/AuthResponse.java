package com.example.storyapp.dto;

public class AuthResponse {
   //region 1 . Attribute
    private String token;
	//endregion
	//region 2. Constructors
    public AuthResponse() {}
    public AuthResponse(String message) {
        this.token = message;
    }
	//endregion
	//region 3. Getter & Setter
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
	//endregion
}
