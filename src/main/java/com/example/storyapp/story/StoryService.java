package com.example.storyapp.story;

import com.example.storyapp.dto.CreateStoryDTO;
import com.example.storyapp.dto.StoryDTO;
import com.example.storyapp.dto.UpdateStoryDTO;
import com.example.storyapp.model.Story;

import com.example.storyapp.model.User;
import com.example.storyapp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface StoryService {
	
	Story createStory(CreateStoryDTO createRequest);
	void updateStory(Long storyId, UpdateStoryDTO updateRequest);
	void deleteStory(Long storyId);
	void  publishStory(Long storyId);
	List<Story> getAllStories();
	Story getStoryById(Long id);
	Page<Story> searchStories(String searchTerm, Pageable pageable);
	
	
	
	
}
