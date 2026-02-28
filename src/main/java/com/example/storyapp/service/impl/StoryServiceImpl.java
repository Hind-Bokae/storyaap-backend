package com.example.storyapp.service.impl;

import com.example.storyapp.StoryRequest;
import com.example.storyapp.dto.CreateStoryDTO;
import com.example.storyapp.dto.StoryDTO;
import com.example.storyapp.dto.UpdateStoryDTO;
import com.example.storyapp.mapper.StoryMapper;
import com.example.storyapp.model.Story;
import com.example.storyapp.model.User;
import com.example.storyapp.repository.StoryRepository;
import com.example.storyapp.repository.UserRepository;
import com.example.storyapp.story.StoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StoryServiceImpl implements StoryService {
	//region 0. Constant
	private final StoryRepository storyRepository;
	private final UserRepository userRepository;
	//endregion
	//region 1. Attribute
	//endregion
	//region 2.Constructors
	public StoryServiceImpl(UserRepository userRepository, StoryRepository storyRepository) {
		this.userRepository = userRepository;
		this.storyRepository = storyRepository;
	}
	//endregion
	//region 3. Override Methods
	@Override
	public Story createStory(CreateStoryDTO createRequest){
		User currentUser = getAuthenticatedUser();
		Story newStory = StoryMapper.toEntity(createRequest);
		newStory.setAuthor(currentUser);
		newStory.setPublished(false);
		newStory.setCreatedAt(LocalDateTime.now());
		return storyRepository.save(newStory);
	}
	@Override
	public void updateStory(Long storyId, UpdateStoryDTO storyRequest){
		Story existingStory = storyRepository.findById(storyId)
				.orElseThrow(() -> new RuntimeException("Story not found with id: " + storyId));
		User authenticatedUser = getAuthenticatedUser();
		validateStoryOwnership(existingStory, authenticatedUser);
		existingStory.setTitle(storyRequest.getTitle());
		existingStory.setContent(storyRequest.getContent());
		storyRepository.save(existingStory);
	}
	
	@Override
	public void deleteStory(Long storyId){
		Story existingStory = storyRepository.findById(storyId)
				.orElseThrow(() -> new RuntimeException("Story not found with id: " + storyId));
		User currentUser = getAuthenticatedUser();
		validateStoryOwnership(existingStory, currentUser);
		storyRepository.delete(existingStory);
	}
	public void publishStory(Long storyId){
		Story existingStory = storyRepository.findById(storyId)
				.orElseThrow(() -> new RuntimeException("Story not found with id: " + storyId));
		User authenticatedUser = getAuthenticatedUser();
		validateStoryOwnership(existingStory, authenticatedUser);
		existingStory.setPublished(true);
		storyRepository.save(existingStory);
	}
	@Override
	public List<Story>getAllStories(){
		return storyRepository.findAll();
	}
	
	@Override
	public Story getStoryById(Long id){
		return storyRepository.findById(id)
				.orElseThrow(()-> new RuntimeException("Story not found"));
	}
	 @Override
	public Page<Story> searchStories(String searchTerm, Pageable pageable){
		return storyRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTerm,searchTerm,pageable);
	 }
	//endregion
	//region 4. Private Methods
	private void validateStoryOwnership(Story existingStory, User authenticatedUser){
		if (!existingStory.getAuthor().getId().equals(authenticatedUser.getId())) {
			throw new RuntimeException("Forbidden!You are not authorized to update this story");
		}
	}
	
	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String         username       = authentication.getName();
		return userRepository.findByUsername(username).orElseThrow(() ->
				new RuntimeException("Authenticated User not found"));}
	//endregion
	
	
	
	
}
