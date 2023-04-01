package com.juniorsilvacc.fastservice.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.controllers.CategoryController;
import com.juniorsilvacc.fastservice.domain.entities.Category;
import com.juniorsilvacc.fastservice.domain.dtos.CategoryDTO;
import com.juniorsilvacc.fastservice.repositories.CategoryRepository;
import com.juniorsilvacc.fastservice.services.exceptions.DataIntegrityViolationException;
import com.juniorsilvacc.fastservice.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public CategoryDTO findById(Integer id) {
		Category entity = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
						String.format("Categoria com id: %d não encontrado", id)));
	
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

	public CategoryDTO create(Category category) {
		Optional<Category> entity = repository.findByName(category.getName());
		
		if(entity.isPresent()) {
			throw new DataIntegrityViolationException("Está categoria já existe");
		}
		
		Category newCategory = repository.save(category);
		
		CategoryDTO dto = new CategoryDTO(newCategory);
		
		dto.add(linkTo(methodOn(CategoryController.class).findById(dto.getId())).withSelfRel());
		
		return dto;
	}

	public void delete(Integer id) {
		Category entity = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException(
						String.format("Categoria com id: %d não encontrado", id)));
		
		repository.delete(entity);
	}

	public CategoryDTO update(Integer id, Category category) {
		Optional<Category> oldEntity = repository.findById(id);
		
		if(!oldEntity.isPresent()) {
			throw new ObjectNotFoundException(
					String.format("Categoria com id: %d não encontrado", id));
		}
		
		Optional<Category> categoryExit = repository.findByName(category.getName());
		
		if(categoryExit.isPresent() && categoryExit.get().getName() != oldEntity.get().getName()) {
			throw new DataIntegrityViolationException("O nome dessa categoria já existe no sistema");
		}
		
		BeanUtils.copyProperties(category, oldEntity.get(), "id");
		
		var updatedEntity = repository.save(oldEntity.get());
		
		CategoryDTO dto = new CategoryDTO(updatedEntity);
		
		dto.add(linkTo(methodOn(CategoryController.class).findById(dto.getId())).withSelfRel());
		
		return dto;
	}

}
