package com.example.storyapp.service;

import com.example.storyapp.dto.AuthenticationRequest;
import com.example.storyapp.dto.RegisterRequest;
import com.example.storyapp.exception.UserAlreadyExistsException;
import com.example.storyapp.model.Role;
import com.example.storyapp.model.User;
import com.example.storyapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
	private final UserRepository  userRepository;
	private final PasswordEncoder passwordEncoder;
	public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		System.out.println("Password encoder class: " +passwordEncoder.getClass());
	}
	
	public void register(RegisterRequest request){
		if(userRepository.findByUsername(request.getUsername()).isPresent()){
			throw new UserAlreadyExistsException("Username already exists");
		}
		User newUser=new User();
		newUser.setUsername(request.getUsername());
		newUser.setPassword(passwordEncoder.encode(request.getPassword()));
		newUser.setRole(Role.USER);
		userRepository.save(newUser);
	}
	public void login(AuthenticationRequest request){
		User existingUser= userRepository.findByUsername(request.getUsername())
				.orElseThrow(()->new RuntimeException("user not found"));
		if (!passwordEncoder.matches(request.getPassword(),existingUser.getPassword())){
			throw new RuntimeException("Invalid password");
		}
	}
	
}
