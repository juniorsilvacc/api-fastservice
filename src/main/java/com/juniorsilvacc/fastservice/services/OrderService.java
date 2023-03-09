package com.juniorsilvacc.fastservice.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.controllers.OrderController;
import com.juniorsilvacc.fastservice.domain.dtos.OrderDTO;
import com.juniorsilvacc.fastservice.domain.entities.Order;
import com.juniorsilvacc.fastservice.repositories.OrderRepository;
import com.juniorsilvacc.fastservice.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public OrderDTO findById(Integer id) {
		Order entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Pedido com id: %d não encontrado", id)));
		
		OrderDTO dto = new OrderDTO(entity);
		
		dto.add(linkTo(methodOn(OrderController.class).findById(id)).withSelfRel());
		
		return dto;
	}

	public List<OrderDTO> findAll() {
		var orders = repository.findAll();
		
		var list = orders.stream().map(OrderDTO::new).collect(Collectors.toList());
		
		list.stream().forEach(p -> p.add(linkTo(methodOn(OrderController.class).findById(p.getId())).withSelfRel()));
		
		return list;
	}


}
