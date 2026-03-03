package com.example.storyapp.security;

import com.example.storyapp.repository.StoryRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class StorySecurity {
	private final StoryRepository storyRepository;
	public StorySecurity(StoryRepository storyRepository) {
		this.storyRepository = storyRepository;
	}
	public boolean isStoryOwner(Long storyId){
		String username=
				SecurityContextHolder
						.getContext()
						.getAuthentication()
						.getName();
		return storyRepository.findById(storyId)
				.map(story ->
						story.getAuthor()
								.getUsername()
								.equals(username))
				.orElse(false);
	}

}
