package com.example.storyapp;

import com.example.storyapp.model.Role;
import com.example.storyapp.model.User;
import com.example.storyapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class StoryappApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(StoryappApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(UserRepository userRepository,PasswordEncoder encoder){
	return args -> {
		if (userRepository.count()==0){
			userRepository.save(
					new User(null,"admin", encoder.encode("admin123"),Role.ADMIN)
			);
			userRepository.save(
					new User(null,"user", encoder.encode("user123"),Role.USER)
			);
		}
	};
	
	}
	
	
	

}
