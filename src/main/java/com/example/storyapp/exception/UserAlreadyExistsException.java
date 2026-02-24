package com.example.storyapp.exception;

public class UserAlreadyExistsException extends RuntimeException {
	public UserAlreadyExistsException(String message) {
		super(message);
	}
	
}
