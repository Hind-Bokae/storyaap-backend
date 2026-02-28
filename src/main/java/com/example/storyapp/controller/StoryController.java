package com.example.storyapp.controller;

import com.example.storyapp.dto.CreateStoryDTO;
import com.example.storyapp.dto.PageResponseDTO;
import com.example.storyapp.dto.StoryDTO;
import com.example.storyapp.dto.UpdateStoryDTO;
import com.example.storyapp.mapper.StoryMapper;
import com.example.storyapp.model.Story;
import com.example.storyapp.story.StoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stories")
public class StoryController {
	private final StoryService storyService;
	public StoryController(StoryService storyService){
		this.storyService=storyService;
	}
	@PostMapping
	public StoryDTO createStory(@Valid @RequestBody CreateStoryDTO createDto){
		Story savedStory=storyService.createStory(createDto);
		return StoryMapper.toDTO(savedStory);
	}
	@GetMapping
	public List<StoryDTO> getAllStories(){
		return storyService.getAllStories().stream().map(StoryMapper::toDTO).toList();
	}
	@GetMapping("/{id}")
	public StoryDTO getStoryById(@PathVariable Long id){
		Story story =storyService.getStoryById(id);
		return StoryMapper.toDTO(story);
	}
	
	@GetMapping(value = "/search",produces = "application/json")
	public PageResponseDTO<StoryDTO> searchStories(@RequestParam String searchTerm, Pageable pageable)
	{
		Page<Story> pageResult=
				 storyService.searchStories(searchTerm,pageable);
		List<StoryDTO> data =pageResult
				.getContent()
				.stream().map(StoryMapper::toDTO)
				.toList();
		return new PageResponseDTO <>(
				data,
				pageResult.getNumber(),
				pageResult.getSize(),
				pageResult.getTotalElements(),
				pageResult.getTotalPages()
		);
		
	}
	@PutMapping("/{id}")
	public void updateStory(@PathVariable Long id, @Valid @RequestBody UpdateStoryDTO updateDto){
		storyService.updateStory(id,updateDto);
		
	}
	@DeleteMapping("/{id}")
	public void deleteStory(@PathVariable Long id){
		Story story = storyService.getStoryById(id);
		storyService.deleteStory(id);
	}
}
