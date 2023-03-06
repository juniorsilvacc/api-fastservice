package com.juniorsilvacc.fastservice.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.controllers.ProductController;
import com.juniorsilvacc.fastservice.domain.Product;
import com.juniorsilvacc.fastservice.dto.ProductDTO;
import com.juniorsilvacc.fastservice.repositories.ProductRepository;
import com.juniorsilvacc.fastservice.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public ProductDTO findById(Integer id) {
		Product entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Produto com id: %d não encontrado", id)));
		
		ProductDTO dto = new ProductDTO(entity);
		
		dto.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
		
		return dto;
	}

}
