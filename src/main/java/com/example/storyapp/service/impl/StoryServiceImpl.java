package com.example.storyapp.service.impl;

import com.example.storyapp.dto.StoryRequest;
import com.example.storyapp.dto.StoryResponse;
import com.example.storyapp.exception.ForbiddenException;
import com.example.storyapp.exception.StoryNotFoundException;
import com.example.storyapp.model.Role;
import com.example.storyapp.model.Story;
import com.example.storyapp.model.User;
import com.example.storyapp.repository.StoryRepository;
import com.example.storyapp.repository.UserRepository;
import com.example.storyapp.story.StoryService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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
	public Story createStory(StoryRequest createRequest){
		String username = SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		User user = userRepository
				.findByUsername(username)
				.orElseThrow();
		Story newStory= new Story();
		newStory.setTitle(createRequest.getTitle());
		newStory.setContent(createRequest.getContent());
		newStory.setAuthor(user);
		return storyRepository.save(newStory);
	}
	@Override
	public Story updateStory(Long storyId, StoryRequest storyRequest){
		Story existingStory = storyRepository.findById(storyId)
				.orElseThrow(() -> new RuntimeException("Story not found with id: " + storyId));
		User authenticatedUser = getAuthenticatedUser();
		validateStoryOwnership(existingStory, authenticatedUser);
		existingStory.setTitle(storyRequest.getTitle());
		existingStory.setContent(storyRequest.getContent());
		return storyRepository.save(existingStory);
	}
	@Override
	public void deleteStoryById(Long storyId){
		Story existingStory = storyRepository.findById(storyId)
				.orElseThrow(() -> new RuntimeException("Story not found with id: " + storyId));
		User currentUser = getAuthenticatedUser();
		validateStoryOwnership(existingStory, currentUser);
		storyRepository.delete(existingStory);
	}
	@Override
	public StoryResponse publishStory(Long storyId){
		Story existingStory = storyRepository.findById(storyId)
				.orElseThrow(() -> new RuntimeException("Story not found with id: " + storyId));
		existingStory.setPublished(true);
		storyRepository.save(existingStory);
		return convertToResponse(existingStory);
	}
	@Override
	public StoryResponse unpublishStory(Long storyId){
		Story existingStory = storyRepository.findById(storyId)
				.orElseThrow(() -> new RuntimeException("Story not found with id: " + storyId));
		existingStory.setPublished(false);
		storyRepository.save(existingStory);
		return convertToResponse(existingStory);
	}
	@Override
	public Story getStoryById(Long id){
		return storyRepository.findById(id)
				.orElseThrow(()-> new StoryNotFoundException("Story not found"));
	}
	 @Override
	public Page<Story> searchStories(String searchTerm, Pageable pageable){
		return storyRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(searchTerm,searchTerm,pageable);
	 }
	@Override
	public Page<StoryResponse> getPublishedStories(String keyword, int page, int size){
		Pageable pageable = PageRequest.of(page, size);
		Page<Story> stories;
		if (keyword == null || keyword.isEmpty()) {
			stories = storyRepository.findByPublishedTrue(pageable);
		} else {
			stories = storyRepository
					.findByPublishedTrueAndTitleContainingIgnoreCase(keyword, pageable);
		}
		return stories.map(this::convertToResponse);
	}
	//endregion
	//region 4. Private Methods
	private void validateStoryOwnership(Story existingStory, User authenticatedUser){
		if (!existingStory.getAuthor().getId().equals(authenticatedUser.getId()) && authenticatedUser.getRole()!= Role.ADMIN) {
			throw new ForbiddenException("You are not allowed");
		}
	}
	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String         username       = authentication.getName();
		return userRepository.findByUsername(username).orElseThrow(() ->
				new RuntimeException("Authenticated User not found"));}
	private StoryResponse convertToResponse(Story story) {
		return new StoryResponse(
				story.getId(),
				story.getTitle(),
				story.getContent(),
				story.getAuthor().getUsername(),
				story.isPublished()
		);
	}
	//endregion
	
	
	
	
}
