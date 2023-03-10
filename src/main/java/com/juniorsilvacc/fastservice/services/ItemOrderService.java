package com.juniorsilvacc.fastservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.domain.entities.ItemOrder;
import com.juniorsilvacc.fastservice.repositories.ItemOrderRepository;
import com.juniorsilvacc.fastservice.services.exceptions.ResourceNotFoundException;

@Service
public class ItemOrderService {
	
	@Autowired
	private ItemOrderRepository repository;

}
