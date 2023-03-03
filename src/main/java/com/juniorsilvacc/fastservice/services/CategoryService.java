package com.juniorsilvacc.fastservice.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.controllers.CategoryController;
import com.juniorsilvacc.fastservice.domain.Category;
import com.juniorsilvacc.fastservice.dto.CategoryDTO;
import com.juniorsilvacc.fastservice.repositories.CategoryRepository;
import com.juniorsilvacc.fastservice.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public CategoryDTO findById(Integer id) {
		Category entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Categoria com id: %d não encontrado", id)));
	
		CategoryDTO dto = new CategoryDTO(entity);
		
		dto.add(linkTo(methodOn(CategoryController.class).findById(id)).withSelfRel());
		
		return dto;
	}

	public List<CategoryDTO> findAll() {
		var categories = repository.findAll();
		
		var list = categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
				
		list.stream().forEach(p -> p.add(linkTo(methodOn(CategoryController.class).findById(p.getId())).withSelfRel()));

		return list;
	}

}
