package com.juniorsilvacc.fastservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.dtos.OrderDTO;
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

}
