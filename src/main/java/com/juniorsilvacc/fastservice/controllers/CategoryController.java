package com.juniorsilvacc.fastservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.dtos.CategoryDTO;
import com.juniorsilvacc.fastservice.domain.entities.Category;
import com.juniorsilvacc.fastservice.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/categories/v1")
@Tag(name = "Category", description = "Endpoints for Managing Category")
@EnableMethodSecurity(prePostEnabled = true)
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping(value = "/{id}")
	@Operation(
			summary = "Get a category",
			description = "Get a category",
			tags = {"Category"},
			responses = {
					@ApiResponse(
							description = "Sucess", 
							responseCode = "200", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public CategoryDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@GetMapping(value = "/")
	@Operation(
		summary = "Finds all category", 
		description = "Finds all category", 
		tags = {"Category"},
		responses = {
				@ApiResponse(
						description = "Sucess", 
						responseCode = "200", 
						content = 
								@Content (
										mediaType = "application/json",
										array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))
									)),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
	)
	public List<CategoryDTO> findAll() {
		return service.findAll();
	}
	
	@PostMapping(value = "/")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(
		summary = "Created a category",
		description = "Add a new category by passing in a JSON or XML representation of the category",
		tags = {"Category"},
		responses = {
				@ApiResponse(
						description = "Created", 
						responseCode = "201", 
						content = 
								@Content (
										mediaType = "application/json",
										array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))
									)),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
	)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CategoryDTO create(@RequestBody Category category) {
		return service.create(category);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(
			summary = "Delete a category",
			description = "Delete a category by passing in a JSON or XML representation of the category",
			tags = {"Category"},
			responses = {
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PatchMapping(value = "/{id}")
	@Operation(
			summary = "Update a category",
			description = "Update a category by passing in a JSON or XML representation of the category",
			tags = {"Category"},
			responses = {
					@ApiResponse(
							description = "Sucess", 
							responseCode = "200", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = CategoryDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public CategoryDTO update(@PathVariable Integer id, @RequestBody Category category) {
		return service.update(id, category);
	}
}
