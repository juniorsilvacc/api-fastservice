package com.juniorsilvacc.fastservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.dtos.CategoryDTO;
import com.juniorsilvacc.fastservice.domain.dtos.OrderDTO;
import com.juniorsilvacc.fastservice.domain.entities.Order;
import com.juniorsilvacc.fastservice.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/orders/v1")
@Tag(name = "Order", description = "Endpoints for Managing Order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping(value = "/{id}")
	@Operation(
			summary = "Get a order",
			description = "Get a order",
			tags = {"Order"},
			responses = {
					@ApiResponse(
							description = "Sucess", 
							responseCode = "200", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public OrderDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@GetMapping(value = "/")
	@Operation(
			summary = "Finds all order",
			description = "Finds all order",
			tags = {"Order"},
			responses = {
					@ApiResponse(
							description = "Sucess", 
							responseCode = "200", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public List<OrderDTO> findAll() {
		return service.findAll();
	}
	
	@PostMapping(value = "/")
	@ResponseStatus(code = HttpStatus.CREATED)
	@Operation(
			summary = "Created a order",
			description = "Add a new order by passing in a JSON or XML representation of the order",
			tags = {"Order"},
			responses = {
					@ApiResponse(
							description = "Created", 
							responseCode = "201", 
							content = 
									@Content (
											mediaType = "application/json",
											array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class))
										)),
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public OrderDTO create(@RequestBody Order order) {
		return service.create(order);
	}
	
	@DeleteMapping(value = "/close/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Operation(
			summary = "Close a order",
			description = "Close a order by passing in a JSON or XML representation of the order",
			tags = {"Product"},
			responses = {
					@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
					@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
					@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
					@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
	)
	public void closeOrder(@PathVariable Integer id) {
		service.closeOrder(id);
	}
	
	@PutMapping(value = "/send/{id}")
	@Operation(
			summary = "Send a order",
			description = "Send a order by passing in a JSON or XML representation of the order",
			tags = {"Order"},
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
	public OrderDTO sendOrder(@PathVariable Integer id) {
		return service.send(id);
	}
	
	@PutMapping(value = "/conclude/{id}")
	@Operation(
			summary = "Conclude a order",
			description = "Conclude a order by passing in a JSON or XML representation of the order",
			tags = {"Order"},
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
	public OrderDTO concludeOrder(@PathVariable Integer id) {
		return service.conclude(id);
	}

}
