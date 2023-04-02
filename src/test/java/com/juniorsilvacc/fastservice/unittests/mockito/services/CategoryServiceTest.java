package com.juniorsilvacc.fastservice.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
import com.juniorsilvacc.fastservice.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class CategoryServiceTest {
	
	private static final Integer ID = 1;
	private static final String NAME = "Hambúrguers";
	private static final String DESCRIPTION = "O hambúrguer foi o mais pedido no país durante o último ano.";
	
	private static final String OBJECT_NOT_FOUND = "Categoria com id: %d não encontrado " + ID;
	
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
		
		var result = service.findById(ID);
		
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getLinks());
		
		assertNotNull(result.toString().contains("links: [</api/categories/v1/1>;rel=\"self\"]"));
		
		assertEquals(CategoryDTO.class, result.getClass());
		assertEquals(1, result.getId());
		assertEquals("Hambúrguers", result.getName());
		assertEquals("O hambúrguer foi o mais pedido no país durante o último ano.", result.getDescription());
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(repository.findById(ID))
        	.thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
		
		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Categoria com id: %d não encontrado " + ID, e.getMessage());
		}
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}
	
	private void inputCategory() {
		category = new Category(ID, NAME, DESCRIPTION);
	}

}
