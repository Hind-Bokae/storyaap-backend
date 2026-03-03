package com.example.storyapp.security;

import com.example.storyapp.service.JwtService;
import com.example.storyapp.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final UserRepository userRepository;
	
	public JwtAuthenticationFilter(JwtService jwtService,
	                               UserRepository userRepository) {
		this.jwtService = jwtService;
		this.userRepository = userRepository;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization");
		System.out.println("Auth Header: " + authHeader);
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = authHeader.substring(7);
		
		String username =null;
		try {
			username = jwtService.extractUsername(token);
		} catch (Exception invalidTokenException) {
			filterChain.doFilter(request, response);
			return;
		}
		if (username != null &&
				SecurityContextHolder.getContext().getAuthentication() == null) {
			
			var user = userRepository.findByUsername(username).orElse(null);
			
			if (user != null && jwtService.isTokenValid(token)) {
				
				UserDetails userDetails =
						User.withUsername(user.getUsername())
								.password(user.getPassword())
								.authorities("ROLE_"+user.getRole().name())
								.build();
				UsernamePasswordAuthenticationToken authToken =
						new UsernamePasswordAuthenticationToken(
								userDetails,
								null,
								userDetails.getAuthorities()
						);
				authToken.setDetails(
						new WebAuthenticationDetailsSource()
								.buildDetails(request)
				);
				
				SecurityContextHolder.getContext()
						.setAuthentication(authToken);
			}
			System.out.println("Username:"+username);
		}
		filterChain.doFilter(request, response);
	}
}