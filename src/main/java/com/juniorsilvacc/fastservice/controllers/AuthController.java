package com.juniorsilvacc.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.dtos.security.AccountCredentialsDTO;
import com.juniorsilvacc.fastservice.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Authentication Endpoint")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin")
	public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO data) {
		if(checkIfParamsNotNull(data)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		}
		
		var token = service.signin(data);
		
		if(token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		} else {
			return token;
		}
	}

	private boolean checkIfParamsNotNull(AccountCredentialsDTO data) {
		return data == null ||  data.getEmail() == null || data.getEmail().isBlank()
				|| data.getPassword() == null || data.getPassword().isBlank();
	}
	
	@Operation(summary = "Refresh token for Authenticated user and returns a token")
	@PutMapping(value = "/refresh/{email}")
	public ResponseEntity<?> refreshToken(@PathVariable("email") String email, @RequestHeader("Authorization") String refreshToken) {
		if(checkIfParamsNotNull(email, refreshToken)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		}
		
		var token = service.refreshToken(email, refreshToken);
		
		if(token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
		} else {
			return token;
		}
	}

	private boolean checkIfParamsNotNull(String email, String refreshToken) {
		return email == null || email.isBlank() || refreshToken == null || refreshToken.isBlank();
	}

}