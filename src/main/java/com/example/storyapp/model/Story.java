package com.example.storyapp.model;

import jakarta.persistence.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDateTime;

@Entity
public class Story {
	
	//region 0. Constant
	//endregion
	
	//region 1. Attribute
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@Column (length = 5000)
	private String content;
	@Column(nullable = false)
	private boolean published=false;
	private LocalDateTime createdAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "user_id", nullable = false)
	 private User author;
	//endregion
	 //region 2.Constructors
	
	public Story(){}
	public Story(String title, String content, User author){
		this.title=title;
		this.content=content;
		this.author=author;
		
	}
	//endregion
	//region 3.Getter & Setter
	public Long getId(){return id;}
	public void setId(Long id){this.id=id;}
	
	public String getTitle(){return title;}
	public void setTitle(String title){this.title=title;}
	
	public String getContent(){return content;}
	public void setContent(String content){this.content=content;}
	
	public void setPublished(boolean published) {
		this.published = published;
	}
	
	public boolean isPublished() {
		return published;
	}
	public User getAuthor(){return author;}
	public void setAuthor(User author){this.author=author;}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	//endregion
	
	
}
