package com.juniorsilvacc.fastservice.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.juniorsilvacc.fastservice.domain.dtos.ProductDTO;
import com.juniorsilvacc.fastservice.domain.entities.Product;
import com.juniorsilvacc.fastservice.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/products/v1")
@Tag(name = "Product", description = "Endpoints for Managing Product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	@Operation(
			summary = "Get a product",
			description = "Get a product",
			tags = {"Product"},
			responses = {
					@ApiResponse(
							description = "Sucess", 
							responseCode = "200", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public ProductDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@GetMapping(value = "/")
	@Operation(
			summary = "Finds all product",
			description = "Finds all product",
			tags = {"Product"},
			responses = {
					@ApiResponse(
							description = "Sucess", 
							responseCode = "200", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public List<ProductDTO> findAll() {
		return service.findAll();
	}
	
	@PostMapping(value = "/")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(
			summary = "Created a product",
			description = "Add a new product by passing in a JSON or XML representation of the product",
			tags = {"Product"},
			responses = {
					@ApiResponse(
							description = "Created", 
							responseCode = "201", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public ProductDTO create(@RequestBody Product product) {
		return service.create(product);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(
			summary = "Delete a product",
			description = "Delete a product by passing in a JSON or XML representation of the product",
			tags = {"Product"},
			responses = {
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public void remove(@PathVariable Integer id) {
		service.remove(id);
	}
	
	@PatchMapping(value = "/{id}")
	@Operation(
			summary = "Update a product",
			description = "Update a product by passing in a JSON or XML representation of the product",
			tags = {"Product"},
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
	public ProductDTO update(@RequestBody Product product, @PathVariable Integer id) {
		return service.update(product, id);
	}
}
