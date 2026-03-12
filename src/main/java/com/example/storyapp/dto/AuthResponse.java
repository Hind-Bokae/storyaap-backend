package com.example.storyapp.dto;

public class AuthResponse {
   //region 1 . Attribute
    private String token;
	private  String username;
	//endregion
	//region 2. Constructors
    public AuthResponse() {}
    public AuthResponse(String message) {
        this.token = message;
    }
	public AuthResponse(String token, String username) {
		this.token = token;
		this.username = username;
	}
	//endregion
	//region 3. Getter & Setter
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//endregion
}
