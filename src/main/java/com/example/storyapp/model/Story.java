package com.example.storyapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//region 0. Constant
	//endregion
	//region 1. Attribute
	private Long id;
	 private String title;
	 private String content;
	 private String auther;
	//endregion
	 //region 2.Constructors
	
	public Story(){}
	public Story(String title, String content, String auther){
		this.title=title;
		this.content=content;
		this.auther=auther;
	}
	//endregion
	//region 3.Getter & Setter
	public Long getId(){return id;}
	public void setId(Long id){this.id=id;}
	
	public String getTitle(){return title;}
	public void setTitle(String title){this.title=title;}
	
	public String getContent(){return content;}
	public void setContent(String content){this.content=content;}
	
	public String getAuther(){return auther;}
	public void setAuther(String auther){this.auther=auther;}
	//endregion
	
}
