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

import com.juniorsilvacc.fastservice.domain.Category;
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
	
	@PostMapping(value = "/")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CategoryDTO create(@RequestBody Category category) {
		return service.create(category);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PatchMapping(value = "/{id}")
	public CategoryDTO update(@PathVariable Integer id, @RequestBody Category category) {
		return service.update(id, category);
	}
}
