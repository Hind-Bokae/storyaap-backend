package com.example.storyapp.controller;

import com.example.storyapp.model.User;
import com.example.storyapp.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository){
		this.userRepository=userRepository;
	}
	@GetMapping
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
}
