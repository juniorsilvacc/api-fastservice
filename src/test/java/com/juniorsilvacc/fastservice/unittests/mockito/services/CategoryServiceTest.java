package com.juniorsilvacc.fastservice.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.juniorsilvacc.fastservice.domain.dtos.CategoryDTO;
import com.juniorsilvacc.fastservice.domain.entities.Category;
import com.juniorsilvacc.fastservice.repositories.CategoryRepository;
import com.juniorsilvacc.fastservice.services.CategoryService;
import com.juniorsilvacc.fastservice.services.exceptions.DataIntegrityViolationException;
import com.juniorsilvacc.fastservice.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class CategoryServiceTest {
	
	private static final Integer ID = 1;
	private static final String NAME = "Hambúrguers";
	private static final String DESCRIPTION = "O hambúrguer foi o mais pedido no país durante o último ano.";
	
	private static final String OBJECT_NOT_FOUND = "Categoria com id: %d não encontrado " + ID;
	private static final String OBJECT_EXISTS = "Está categoria já existe";
	
	@InjectMocks
	private CategoryService service;
	
	@Mock
	CategoryRepository repository;
	
	private Category category;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		inputCategory();
	}

	@Test
	void whenFindByIdThenReturnAnCategoryInstance() {
		when(repository.findById(ID)).thenReturn(Optional.of(category));
		
		var response = service.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getLinks());
		
		assertNotNull(response.toString().contains("links: [</api/categories/v1/1>;rel=\"self\"]"));
		
		assertEquals(CategoryDTO.class, response.getClass());
		assertEquals(1, response.getId());
		assertEquals("Hambúrguers", response.getName());
		assertEquals("O hambúrguer foi o mais pedido no país durante o último ano.", response.getDescription());
	}

	@Test
	void whenFindAllThenReturnAnListOfCategories() {
		when(repository.findAll()).thenReturn(List.of(category));
		
		List<CategoryDTO> response = service.findAll();
		
		assertEquals(1, response.size());
		
		var objCategory1 = response.get(0);
		
		assertNotNull(response);
		
		assertNotNull(objCategory1.getLinks());
		assertNotNull(objCategory1.toString().contains("links: [</api/categories/v1/1>;rel=\"self\"]"));
		
		
		assertEquals(CategoryDTO.class, objCategory1.getClass());
		
		assertEquals(ID, objCategory1.getId());
		assertEquals(NAME, objCategory1.getName());
		assertEquals(DESCRIPTION, objCategory1.getDescription());
	}

	@Test
	void whenCreateThenReturnSucess() {
		Category entity = new Category(ID, NAME, DESCRIPTION);
		Category persisted = entity;
		
		when(repository.save(entity)).thenReturn(persisted);
		
		CategoryDTO response = service.create(persisted);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getLinks());
		
		assertNotNull(response.toString().contains("links: [</api/categories/v1/1>;rel=\"self\"]"));
		
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(DESCRIPTION, response.getDescription());
	}
	
	@Test
	void deleteWithSuccess() {
		Category entity = new Category(ID, NAME, DESCRIPTION);
		entity.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(entity));
		
		service.delete(1);
	}

	@Test
	void whenUpdateThenReturnSuccess() {
		Category entity = new Category(ID, NAME, DESCRIPTION);
		Category persisted = entity;
		persisted.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(persisted);
		
		var response = service.update(1, entity);

        assertNotNull(response);
        assertEquals(CategoryDTO.class, response.getClass());
        
        assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getLinks());
		
		assertNotNull(response.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
        
        assertEquals(1, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(DESCRIPTION, response.getDescription());
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
	void whenFindByEmailThenReturnDataIntegrityViolationException() {
		when(repository.findByName(NAME))
    		.thenThrow(new DataIntegrityViolationException(OBJECT_EXISTS));
		
		try {
			service.create(category);
		} catch (Exception e) {
			assertEquals(DataIntegrityViolationException.class, e.getClass());
            assertEquals(OBJECT_EXISTS, e.getMessage());
		}
	}
	
	private void inputCategory() {
		category = new Category(ID, NAME, DESCRIPTION);
	}

}
