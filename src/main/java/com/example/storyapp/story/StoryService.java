package com.example.storyapp.story;

import com.example.storyapp.dto.StoryRequest;
import com.example.storyapp.dto.StoryResponse;
import com.example.storyapp.model.Story;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoryService {
	
	Story createStory(StoryRequest createRequest);
	Story updateStory(Long storyId, StoryRequest storyRequest);
	void deleteStoryById(Long storyId);
	Story getStoryById(Long id);
	Page<Story> searchStories(String searchTerm, Pageable pageable);
	StoryResponse publishStory(Long storyId);
	StoryResponse unpublishStory(Long storyId);
	Page<StoryResponse> getPublishedStories(String keyword, int page, int size);
	
	
	
}
