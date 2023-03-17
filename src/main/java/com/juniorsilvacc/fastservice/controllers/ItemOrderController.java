package com.juniorsilvacc.fastservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.dtos.ItemOrderDTO;
import com.juniorsilvacc.fastservice.domain.entities.ItemOrder;
import com.juniorsilvacc.fastservice.services.ItemOrderService;

@RestController
@RequestMapping(value = "/api/items/v1")
public class ItemOrderController {
	
	@Autowired
	private ItemOrderService service;
	
	@PostMapping(value = "/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ItemOrderDTO create(@RequestBody ItemOrder itemOrder) {
		return service.create(itemOrder);
	}
	
}
