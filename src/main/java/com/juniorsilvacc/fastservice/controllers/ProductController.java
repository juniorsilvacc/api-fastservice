package com.juniorsilvacc.fastservice.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.dto.ProductDTO;
import com.juniorsilvacc.fastservice.services.ProductService;

@RestController
@RequestMapping(value = "/api/products/v1")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value = "/{id}")
	public ProductDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}

}
