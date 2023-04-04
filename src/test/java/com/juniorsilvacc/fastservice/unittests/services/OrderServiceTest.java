package com.juniorsilvacc.fastservice.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.juniorsilvacc.fastservice.domain.dtos.OrderDTO;
import com.juniorsilvacc.fastservice.domain.entities.Order;
import com.juniorsilvacc.fastservice.domain.enums.Status;
import com.juniorsilvacc.fastservice.repositories.OrderRepository;
import com.juniorsilvacc.fastservice.services.OrderService;

@SpringBootTest
class OrderServiceTest {
	
	private static final Integer ID = 1;
	private static final String NAME = "Júniior Silva";
	private static final Integer TABLE = 10;
	private static final Boolean DRAFT = true;
	private static final OffsetDateTime MOMENT = OffsetDateTime.now();
	private static final Status STATUS = Status.PENDING;
	
	@InjectMocks
	private OrderService service;
	
	@Mock
	OrderRepository repository;
	
	private Order order;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		inputOrder();
	}

	@Test
	void whenFindByIdThenReturnAnOrderInstance() {
		when(repository.findById(ID)).thenReturn(Optional.of(order));
		
		var response = service.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getId());
		assertNotNull(response.getLinks());
		
		assertNotNull(response.toString().contains("links: [</api/orders/v1/1>;rel=\"self\"]"));
		
		assertEquals(OrderDTO.class, response.getClass());
		
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(TABLE, response.getTable());
		assertEquals(DRAFT, response.getDraft());
		assertEquals(MOMENT, response.getMoment());
		assertEquals(STATUS, response.getStatus());
	}
	
	private void inputOrder() {
		order = new Order(ID, NAME, TABLE, DRAFT, MOMENT, STATUS);
	}

}
