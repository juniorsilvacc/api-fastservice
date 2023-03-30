package com.juniorsilvacc.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.dtos.security.AccountCredentialsDTO;
import com.juniorsilvacc.fastservice.domain.entities.User;
import com.juniorsilvacc.fastservice.security.JwtToken;
import com.juniorsilvacc.fastservice.services.exceptions.InvalidAuthenticationException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/auth")
@Tag(name = "Authentication Endpoint")
public class AuthenticationController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
    private JwtToken jwtToken;
	
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin")
	public String signin(@RequestBody AccountCredentialsDTO data) {
		if(data == null ||  data.getEmail() == null || data.getEmail().isBlank()
				|| data.getPassword() == null || data.getPassword().isBlank()) {
			throw new InvalidAuthenticationException("Solicitação de usuário inválida");
		}
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= 
				new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword());
		
		 Authentication authenticate = this.authenticationManager
	                .authenticate(usernamePasswordAuthenticationToken);
		 
		 var user = (User) authenticate.getPrincipal();
		 
		 return jwtToken.generateToken(user);
	}

}
