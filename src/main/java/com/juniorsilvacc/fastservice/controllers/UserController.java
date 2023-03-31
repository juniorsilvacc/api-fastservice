package com.juniorsilvacc.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.juniorsilvacc.fastservice.domain.dtos.UserDTO;
import com.juniorsilvacc.fastservice.domain.entities.User;
import com.juniorsilvacc.fastservice.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
	@Operation(
			summary = "Create a new user waiter",
			description = "Add a new user in a JSON representation of the user",
			tags = {"User"},
			responses = {
					@ApiResponse(
							description = "Created", 
							responseCode = "201", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = UserDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserDTO create(@RequestBody User user) {
		return service.create(user);
	}
	
	@PutMapping(value = "/upload/avatar")
	public UserDTO uploadAvatar(@AuthenticationPrincipal User user, @RequestParam("file") MultipartFile image) {
		return service.uploadAvatar(user, image);
	}

}
