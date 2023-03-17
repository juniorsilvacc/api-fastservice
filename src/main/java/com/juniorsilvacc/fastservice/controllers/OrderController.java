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

import com.juniorsilvacc.fastservice.domain.dtos.OrderDTO;
import com.juniorsilvacc.fastservice.domain.entities.Order;
import com.juniorsilvacc.fastservice.services.OrderService;

@RestController
@RequestMapping(value = "/api/orders/v1")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping(value = "/{id}")
	public OrderDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@GetMapping(value = "/")
	public List<OrderDTO> findAll() {
		return service.findAll();
	}
	
	@PostMapping(value = "/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrderDTO create(@RequestBody Order order) {
		return service.create(order);
	}
	
	@DeleteMapping(value = "/close/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void closeOrder(@PathVariable Integer id) {
		service.closeOrder(id);
	}
	
	@PutMapping(value = "/send/{id}")
	public OrderDTO sendOrder(@PathVariable Integer id) {
		return service.send(id);
	}
	
	@PutMapping(value = "/conclude/{id}")
	public OrderDTO concludeOrder(@PathVariable Integer id) {
		return service.conclude(id);
	}

}
