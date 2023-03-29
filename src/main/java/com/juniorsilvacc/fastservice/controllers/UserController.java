package com.juniorsilvacc.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.dtos.UserDTO;
import com.juniorsilvacc.fastservice.domain.entities.User;
import com.juniorsilvacc.fastservice.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/users/v1")
@Tag(name = "User", description = "Endpoints for Managing User")
@EnableMethodSecurity(prePostEnabled = true)
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserDTO create(@RequestBody User user) {
		return service.create(user);
	}

}
