package com.juniorsilvacc.fastservice.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.List;
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
	private static final OffsetDateTime MOMENT = OffsetDateTime.parse("2019-06-20T19:53:07Z");
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
	
	@Test
	void whenFindAllThenReturnAnListOfOrders() {
		when(repository.findAll()).thenReturn(List.of(order));
		
		List<OrderDTO> response = service.findAll();
		
		assertEquals(1, response.size());
		
		var objOrders1 = response.get(0);
		
		assertNotNull(response);
		
		assertNotNull(objOrders1.getLinks());
		assertNotNull(response.toString().contains("links: [</api/orders/v1/1>;rel=\"self\"]"));
		
		
		assertEquals(OrderDTO.class, objOrders1.getClass());
		
		assertEquals(ID, objOrders1.getId());
		assertEquals(NAME, objOrders1.getName());
		assertEquals(TABLE, objOrders1.getTable());
		assertEquals(DRAFT, objOrders1.getDraft());
		assertEquals(MOMENT, objOrders1.getMoment());
		assertEquals(STATUS, objOrders1.getStatus());
	}
	
	@Test
	void whenCreatedOrderReturnsSuccessfully() {
		Order entity = new Order(ID, NAME, TABLE, DRAFT, MOMENT, STATUS);
		Order persisted = entity;
		
		when(repository.save(entity)).thenReturn(persisted);
		
		OrderDTO response = service.create(persisted);
		
		assertNotNull(response);
		assertNotNull(response.getLinks());
		assertNotNull(response.toString().contains("links: [</api/orders/v1/1>;rel=\"self\"]"));
		
		
		assertEquals(OrderDTO.class, response.getClass());
		
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getName());
		assertEquals(TABLE, response.getTable());
		assertEquals(DRAFT, response.getDraft());
		assertEquals(MOMENT, OffsetDateTime.parse("2019-06-20T19:53:07Z"));
		assertEquals(STATUS, response.getStatus());
	}
	
	@Test
	void closeOrder() {
		Order entity = new Order(ID, NAME, TABLE, DRAFT, MOMENT, STATUS);
		entity.setId(1);
		
		when(repository.findById(1)).thenReturn(Optional.of(order));
		
		repository.delete(entity);
	}
	
	private void inputOrder() {
		order = new Order(ID, NAME, TABLE, DRAFT, MOMENT, STATUS);
	}

}
