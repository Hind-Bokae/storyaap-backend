package com.example.storyapp.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
	//region 1 Attributes
    private String message;
    private int status;
    private LocalDateTime timestamp;
	//endregion
	//region 2 Constructors
    public ErrorResponse() {}
    public ErrorResponse(String message) {
        this.message = message;
    }
    public ErrorResponse(int status,String message) {
	    this.status = status;
        this.message = message;
		this.timestamp = LocalDateTime.now();
    }
	//endregion
	//region 3 Getters and Setters
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
	//endregion
}
