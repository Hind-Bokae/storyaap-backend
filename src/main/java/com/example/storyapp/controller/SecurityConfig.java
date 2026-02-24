package com.example.storyapp.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity securityBuilder) throws Exception{
		securityBuilder
				.csrf(csrfConfig ->csrfConfig.disable())
				.authorizeHttpRequests(requestConfig ->requestConfig.anyRequest().permitAll());
		return securityBuilder.build();
	}
}
