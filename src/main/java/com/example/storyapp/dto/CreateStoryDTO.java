package com.example.storyapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateStoryDTO {
	
	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 100, message = "Title muss be 3-100 characters")
	private String title;
	
	@NotBlank(message = "Content is required")
	@Size(min = 10, message = "Content min 10 chare")
	private String content;
	
	
	
	public @NotBlank(message = "Title is required") @Size(min = 3, max = 100, message = "Title muss be 3-100 characters") String getTitle() {
		return title;
	}
	
	public void setTitle(@NotBlank(message = "Title is required") @Size(min = 3, max = 100, message = "Title muss be 3-100 characters") String title) {
		this.title = title;
	}
	
	public @NotBlank(message = "Content is required") @Size(min = 10, message = "Content min 10 chare") String getContent() {
		return content;
	}
	
	public void setContent(@NotBlank(message = "Content is required") @Size(min = 10, message = "Content min 10 chare") String content) {
		this.content = content;
	}
	
}

