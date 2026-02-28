package com.example.storyapp.mapper;

import com.example.storyapp.dto.CreateStoryDTO;
import com.example.storyapp.dto.StoryDTO;
import com.example.storyapp.model.Story;
import com.example.storyapp.repository.StoryRepository;

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
	
	
}
