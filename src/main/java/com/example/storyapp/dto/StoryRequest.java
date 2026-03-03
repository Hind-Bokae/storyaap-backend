package com.example.storyapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StoryRequest {
	
	//region 1. Attributes
	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
	private String title;
	
	@NotBlank(message = "Content is required")
	@Size(min = 10, message = "Content must be at least 10 characters long")
	private String content;
	
	//endregion
	
	//region 2. Constructors
	public StoryRequest() {}
	
	public StoryRequest(String title, String content, String author) {
		this.title = title;
		this.content = content;
	}
	//endregion
	
	//region 3. Getters & Setters
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	//endregion
}
