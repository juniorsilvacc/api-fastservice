package com.juniorsilvacc.fastservice.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import com.juniorsilvacc.fastservice.domain.dtos.ProductDTO;
import com.juniorsilvacc.fastservice.domain.entities.Product;
import com.juniorsilvacc.fastservice.repositories.ProductRepository;
import com.juniorsilvacc.fastservice.services.ProductService;
import com.juniorsilvacc.fastservice.services.exceptions.DataIntegrityViolationException;
import com.juniorsilvacc.fastservice.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ProductServiceTest {
	
	private static final Integer ID = 1;
	private static final String NAME = "Hambúrguer de Calabresa";
	private static final String DESCRIPTION = "Hambúrguer de 450g recheado de catupity com calabresa";
	private static final Double PRICE = 30.0;
	private static final String IMAGE = "fresh-pepper.jpg";
	
	private static final String OBJECT_NOT_FOUND = "Produto com id: %d não encontrado " + ID;
	private static final String OBJECT_EXISTS = "Está categoria já existe";
	
	@InjectMocks
	private ProductService service;
	
	@Mock
	ProductRepository repository;
	
	private Product product;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		inputProduct();
	}
	
	@Test 
	void whenFindByIdThenReturnAnProductInstance() {
		when(repository.findById(ID)).thenReturn(Optional.of(product));
		
		var response = service.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getLinks());
		
		assertNotNull(response.toString().contains("links: [</api/products/v1/1>;rel=\"self\"]"));
		
		assertEquals(ProductDTO.class, response.getClass());
		
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(DESCRIPTION, response.getDescription());
		assertEquals(PRICE, response.getPrice());
		assertEquals(IMAGE, response.getImage());
	}

	@Test
	void whenCreatedProductReturnsSuccessfully() {
		Product entity = new Product(ID, NAME, DESCRIPTION, PRICE, IMAGE);
		Product persisted = entity;
		
		MockMultipartFile multipartFile = new MockMultipartFile("file",
                "fresh-pepper.jpg",
                "image/png, image/jpg, image/jpeg",
                "random image".getBytes());
 
        when(repository.save(entity)).thenReturn(persisted);
		
		ProductDTO response = service.create(persisted, multipartFile);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getLinks());
		
		assertNotNull(response.toString().contains("links: [</api/products/v1/1>;rel=\"self\"]"));
		
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(DESCRIPTION, response.getDescription());
		assertEquals(PRICE, response.getPrice());
		assertEquals(IMAGE, response.getImage());
	}
	
	@Test
	void deleteProductWithSuccess() {
		Product entity = new Product(ID, NAME, DESCRIPTION, PRICE, IMAGE);
		entity.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(product));
		
		repository.delete(product);
	}
	
	@Test
	void whenUpdatingProductReturnsSuccessfully() {
		Product entity = new Product(ID, NAME, DESCRIPTION, PRICE, IMAGE);
		Product persisted = entity;
		product.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(product));
		when(repository.save(entity)).thenReturn(persisted);
		
		var response = service.update(entity, 1);
		
		assertNotNull(response);
        assertEquals(ProductDTO.class, response.getClass());
        
        assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getLinks());
		
		assertNotNull(response.toString().contains("links: [</api/products/v1/1>;rel=\"self\"]"));
		
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(DESCRIPTION, response.getDescription());
		assertEquals(PRICE, response.getPrice());
		assertEquals(IMAGE, response.getImage());
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(repository.findById(ID))
        	.thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
		
		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND, e.getMessage());
		}
	}
	
	@Test
	void whenFindByNameThenReturnDataIntegrityViolationException() {
		when(repository.findByName(NAME))
    		.thenThrow(new DataIntegrityViolationException(OBJECT_EXISTS));
		
		try {
			MockMultipartFile multipartFile = new MockMultipartFile("file",
	                "fresh-pepper.jpg",
	                "image/png, image/jpg, image/jpeg",
	                "random image".getBytes());
	 
			service.create(product, multipartFile);
		} catch (Exception e) {
			assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(OBJECT_EXISTS, e.getMessage());
		}
	}
	
	private void inputProduct() {
		product = new Product(ID, NAME, DESCRIPTION, PRICE, IMAGE);
	}

}
