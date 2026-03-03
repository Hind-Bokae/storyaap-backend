package com.example.storyapp.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
	private static final String SECRET =
			"mySuperSecretKeyThatIsAtLeast32CharactersLong";
	private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
	private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
	
	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key)
				.compact();
	}
	public String extractUsername(String token) {
		return Jwts.parser()
				.verifyWith(key)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser()
					.verifyWith(key)
					.build()
					.parseSignedClaims(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}