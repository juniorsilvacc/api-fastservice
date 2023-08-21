package com.juniorsilvacc.fastservice.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.juniorsilvacc.fastservice.domain.dtos.security.TokenDTO;
import com.juniorsilvacc.fastservice.domain.entities.User;

@Service
public class JwtToken {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	public TokenDTO	createAccessToken (User user) {
		Date now = new Date();
		Date validity = new Date(now.getTime() + expiration);
		var accessToken = generateToken(user);
		
		return new TokenDTO(user.getEmail(), true, now, validity, accessToken);
		
	}

	public String generateToken(User user) {
		try {
			
			String issuerURL = ServletUriComponentsBuilder
					.fromCurrentContextPath().build().toUriString();
			
			return JWT.create()
	                .withIssuer(issuerURL)
	                .withSubject(user.getEmail())
	                .withClaim("id", user.getId())
	                .withClaim("roles", user.getRoles())
	                .withExpiresAt(new Date(System.currentTimeMillis() + expiration)) 
	                .sign(Algorithm.HMAC256(secret))
	                .strip();
			
		} catch (JWTCreationException e) {
			throw new RuntimeException("Erro ao gerar JWT Token");
		}
	}
	
	public String getSubject(String token) {
		try {
			
			String issuerURL = ServletUriComponentsBuilder
					.fromCurrentContextPath().build().toUriString();
			
	        return JWT.require(Algorithm.HMAC256(secret))
	                .withIssuer(issuerURL)
	                .build().verify(token).getSubject();
	        
		} catch (JWTVerificationException e) {
			throw new RuntimeException("Token inválido ou expirado");
		}
    }
	
}
