package com.juniorsilvacc.fastservice.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.domain.entities.Product;
import com.juniorsilvacc.fastservice.domain.dtos.ProductDTO;
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
	
	@GetMapping(value = "/")
	public List<ProductDTO> findAll() {
		return service.findAll();
	}
	
	@PostMapping(value = "/")
	public ProductDTO create(@RequestBody Product product) {
		return service.create(product);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remove(@PathVariable Integer id) {
		service.remove(id);
	}
}
