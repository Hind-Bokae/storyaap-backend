package com.example.storyapp.exception;

public class StoryNotFoundException extends RuntimeException{
	public StoryNotFoundException(String message) {
		super(message);
	}
}
