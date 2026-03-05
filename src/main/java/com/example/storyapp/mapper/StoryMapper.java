package com.example.storyapp.mapper;

import com.example.storyapp.dto.CreateStoryDTO;
import com.example.storyapp.dto.StoryDTO;
import com.example.storyapp.dto.StoryResponse;
import com.example.storyapp.model.Story;

public class StoryMapper {
	public static Story toEntity (CreateStoryDTO dtoStory){
		Story story=new Story();
		story.setTitle(dtoStory.getTitle());
		story.setContent(dtoStory.getContent());
		return story;
	}
	public static StoryDTO toDTO(Story story){
		return new StoryDTO(
				story.getId(),
				story.getTitle(),
				story.getContent(),
				story.getAuthor().getUsername()
		);
	}
	
	public static StoryResponse toResponse(Story savedStory) {
		return new StoryResponse(
				savedStory.getId(),
				savedStory.getTitle(),
				savedStory.getContent(),
				savedStory.getAuthor().getUsername(),
				savedStory.isPublished()
		);
	}
}
