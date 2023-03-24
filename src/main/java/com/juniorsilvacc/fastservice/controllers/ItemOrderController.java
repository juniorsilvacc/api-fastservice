package com.juniorsilvacc.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.dtos.ItemOrderDTO;
import com.juniorsilvacc.fastservice.domain.entities.ItemOrder;
import com.juniorsilvacc.fastservice.services.ItemOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/items/v1")
@Tag(name = "Item", description = "Endpoints for Managing Item")
public class ItemOrderController {
	
	@Autowired
	private ItemOrderService service;
	
	@PostMapping(value = "/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(
			summary = "Add a item",
			description = "Add a new item by passing in a JSON or XML representation of the item",
			tags = {"Item"},
			responses = {
					@ApiResponse(
							description = "Created", 
							responseCode = "201", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = ItemOrderDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public ItemOrderDTO create(@RequestBody ItemOrder itemOrder) {
		return service.create(itemOrder);
	}
	
	@DeleteMapping(value = "/remove/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(
			summary = "Delete a item",
			description = "Delete a item by passing in a JSON or XML representation of the item",
			tags = {"Item"},
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
	
}
