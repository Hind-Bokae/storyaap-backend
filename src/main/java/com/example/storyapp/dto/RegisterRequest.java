package com.example.storyapp.dto;

public class RegisterRequest {
	//region 0. Constant
	//endregion
	//region 1. Attribute
	private String username;
	private String password;
	//endregion
	//region 2.Constructors
	public RegisterRequest() {}
	public RegisterRequest(String username) {
		this.username = username;
	}
	public RegisterRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}
	//endregion
	//region 3.Getter & Setter
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//endregion
}
