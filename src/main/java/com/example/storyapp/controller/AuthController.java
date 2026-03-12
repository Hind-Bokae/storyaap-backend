package com.example.storyapp.controller;

import com.example.storyapp.dto.AuthResponse;
import com.example.storyapp.dto.AuthenticationRequest;
import com.example.storyapp.dto.RegisterRequest;
import com.example.storyapp.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	@PostMapping("/register")
	public AuthResponse register(@RequestBody RegisterRequest request){
		authService.register(request);
		return new AuthResponse("User registered successfully");
	}
	@PostMapping("/login")
	public AuthResponse login (@RequestBody AuthenticationRequest request){
		String token= authService.login(request);
		return new AuthResponse(token , request.getUsername());
	}
}
