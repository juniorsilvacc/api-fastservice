package com.juniorsilvacc.fastservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.dto.CategoryDTO;
import com.juniorsilvacc.fastservice.services.CategoryService;

@RestController
@RequestMapping(value = "/api/categories/v1")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping(value = "/{id}")
	public CategoryDTO findById(@PathVariable Integer id) {
		return service.findById(id);
	}
	
	@GetMapping(value = "/")
	public List<CategoryDTO> findAll() {
		return service.findAll();
	}

}
