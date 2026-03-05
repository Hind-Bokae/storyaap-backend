package com.example.storyapp.controller;

import com.example.storyapp.dto.StoryRequest;
import com.example.storyapp.dto.PageResponseDTO;
import com.example.storyapp.dto.StoryDTO;
import com.example.storyapp.dto.StoryResponse;
import com.example.storyapp.mapper.StoryMapper;
import com.example.storyapp.model.Story;
import com.example.storyapp.repository.StoryRepository;
import com.example.storyapp.story.StoryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stories")
public class StoryController {
	//region 0. Constant
	private final StoryService storyService;
	//endregion
	//region 1. Attribute
	//endregion
	//region 2.Constructors
	public StoryController(StoryService storyService){
		this.storyService=storyService;
	}
	//endregion
	//region 3. CRUD Methods und Other Endpoints
	@PostMapping
	public StoryResponse createStory(@Valid @RequestBody StoryRequest request){
		Story savedStory=storyService.createStory(request);
		return StoryMapper.toResponse(savedStory);
	}
	@PreAuthorize("@storySecurity.isStoryOwner(#id)")
	@PutMapping("/{id}")
	public Story updateStory(
			@PathVariable Long id,
			@Valid @RequestBody StoryRequest Request){
		return storyService.updateStory(id,Request);
	}
	@PreAuthorize("hasRole('ADMIN') or @storySecurity.isStoryOwner(#id)")
	@DeleteMapping("/{id}")
	public void deleteStory(@PathVariable Long id){
		storyService.deleteStoryById(id);
	}
	
	@GetMapping("/{id}")
	public StoryResponse getStoryById(@PathVariable Long id){
		Story story =storyService.getStoryById(id);
		return StoryMapper.toResponse(story);
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
	
	@PreAuthorize("@storySecurity.isStoryOwner(#id) or hasRole('ADMIN')")
	@PutMapping("/{id}/publish")
	public StoryResponse publishStory(@PathVariable Long id) {
		return storyService.publishStory(id);
	}
	@PreAuthorize("@storySecurity.isStoryOwner(#id) or hasRole('ADMIN')")
	@PutMapping("/{id}/unpublish")
	public StoryResponse unpublishStory(@PathVariable Long id) {
		return storyService.unpublishStory(id);
	}
	@GetMapping
	@PreAuthorize("permitAll()")
	public Page<StoryResponse> getStories(
			@RequestParam(defaultValue = "") String keyword,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size
	){
		return storyService.getPublishedStories(keyword,page,size);
	}
}
