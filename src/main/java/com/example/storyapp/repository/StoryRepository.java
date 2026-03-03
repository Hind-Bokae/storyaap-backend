package com.example.storyapp.repository;

import com.example.storyapp.model.Story;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StoryRepository extends JpaRepository<Story, Long>{
	Page<Story> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(
			String title,
			String content,
			Pageable pageable
	);
	Page<Story> findByPublishedTrue(Pageable pageable);
	Page<Story> findByPublishedTrueAndTitleContainingIgnoreCase(
			String keyword,
			Pageable pageable
	);
}

