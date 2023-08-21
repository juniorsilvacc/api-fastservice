package com.juniorsilvacc.fastservice.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.juniorsilvacc.fastservice.domain.entities.ItemOrder;
import com.juniorsilvacc.fastservice.domain.entities.Order;
import com.juniorsilvacc.fastservice.domain.entities.Product;
import com.juniorsilvacc.fastservice.repositories.ItemOrderRepository;
import com.juniorsilvacc.fastservice.services.ItemOrderService;
import com.juniorsilvacc.fastservice.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ItemOrderServiceTest {
	
	private static final Integer ID = 1;
	private static final Integer AMOUNT = 3;
	private Order ORDER;
	private Product PRODUCT;
	
	private static final String OBJECT_NOT_FOUND = "Item com id: %d não encontrado " + ID;
	
	@InjectMocks
	private ItemOrderService service;
	
	@Mock
	ItemOrderRepository repository;
	
	private ItemOrder itemOrder;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		inputItemOrder();
	}

	@Test
	void whenCreatedItemReturnsSuccessfully() {
	}
	
	@Test
	void removeItemWithSuccess() {
		ItemOrder entity = new ItemOrder(ID, AMOUNT, ORDER, PRODUCT);
		entity.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(itemOrder));
		
		repository.delete(itemOrder);
	}
	
	@Test
	void whenFindByIdThenReturnObjectNotFoundException() {
		when(repository.findById(ID))
        	.thenThrow(new ObjectNotFoundException(OBJECT_NOT_FOUND));
		
		try {
			service.remove(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals(OBJECT_NOT_FOUND, e.getMessage());
		}
	}
	
	void inputItemOrder() {
		itemOrder = new ItemOrder(ID, AMOUNT, ORDER, PRODUCT);
	}

}
