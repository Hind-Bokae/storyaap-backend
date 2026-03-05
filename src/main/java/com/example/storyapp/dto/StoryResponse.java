package com.example.storyapp.dto;

public class StoryResponse {
	//region 1 Attributes
	private Long id;
	private String title;
	private String content;
	private String  authorUsername;
	private boolean isPublish;
	//endregion
	//region 2 Constructors
	public StoryResponse() {
	}
	public StoryResponse(Long id, String title, String content, String authorUsername, boolean isPublic) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.authorUsername = authorUsername;
		this.isPublish = isPublic;
	}
	//endregion
	//region 3 Getters and Setters
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getAuthorUsername() {
		return authorUsername;
	}
	public boolean isPublish() {
		return isPublish;
	}
	//endregion
}
