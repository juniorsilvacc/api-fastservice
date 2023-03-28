package com.juniorsilvacc.fastservice.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.juniorsilvacc.fastservice.controllers.ProductController;
import com.juniorsilvacc.fastservice.domain.dtos.ProductDTO;
import com.juniorsilvacc.fastservice.domain.entities.Product;
import com.juniorsilvacc.fastservice.repositories.ProductRepository;
import com.juniorsilvacc.fastservice.services.exceptions.MethodArgumentNotValidException;
import com.juniorsilvacc.fastservice.services.exceptions.ResourceNotFoundException;
import com.juniorsilvacc.fastservice.util.UploadUtil;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;

	public ProductDTO findById(Integer id) {
		Product entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Produto com id: %d não encontrado", id)));
		
		ProductDTO dto = new ProductDTO(entity);
		
		dto.add(linkTo(methodOn(ProductController.class).findById(id)).withSelfRel());
		
		return dto;
	}

	public List<ProductDTO> findAll() {
		var products = repository.findAll();
		
		var list = products.stream().map(ProductDTO::new).collect(Collectors.toList());
		
		list.stream().forEach(p -> p.add(linkTo(methodOn(ProductController.class).findById(p.getId())).withSelfRel()));

		return list;
	}

	public ProductDTO create(Product product, MultipartFile image) {
		Optional<Product> entity = repository.findByName(product.getName());
		
		if(entity.isPresent()) {
			throw new MethodArgumentNotValidException("Esse produto já existe");
		}
			
		if(UploadUtil.uploadImage(image)) {
			product.setImage(image.getOriginalFilename());
		}
			
		Product newProduct = repository.save(product);
			
		ProductDTO dto = new ProductDTO(newProduct);
			
		return dto;
		
	}

	public void remove(Integer id) {
		Product entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("Produto com id: %d não encontrado", id)));
		
		repository.delete(entity);
		
	}

	public ProductDTO update(Product product, Integer id) {
		Optional<Product> oldEntity = repository.findById(id);
		
		if(!oldEntity.isPresent()) {
			throw new ResourceNotFoundException(
					String.format("Produto com id: %d não encontrado", id));
		}
		
		Optional<Product> productExit = repository.findByName(product.getName());
		
		//Se o nome existe EE o nome que está no banco de dados é diferente do que está sendo enviado
		if(productExit.isPresent() && productExit.get().getName() != oldEntity.get().getName()) {
			throw new MethodArgumentNotValidException("O nome desse produto já existe no sistema");
		}
		
		BeanUtils.copyProperties(product, oldEntity.get(), "id");
		
		var updatedEntity = repository.save(oldEntity.get());
		
		ProductDTO dto = new ProductDTO(updatedEntity);
		
		return dto;
	}

}
