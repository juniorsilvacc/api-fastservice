package com.juniorsilvacc.fastservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.domain.dtos.ItemOrderDTO;
import com.juniorsilvacc.fastservice.domain.entities.ItemOrder;
import com.juniorsilvacc.fastservice.domain.entities.Order;
import com.juniorsilvacc.fastservice.domain.entities.Product;
import com.juniorsilvacc.fastservice.repositories.ItemOrderRepository;
import com.juniorsilvacc.fastservice.repositories.OrderRepository;
import com.juniorsilvacc.fastservice.repositories.ProductRepository;
import com.juniorsilvacc.fastservice.services.exceptions.ObjectNotFoundException;

@Service
public class ItemOrderService {
	
	@Autowired
	private ItemOrderRepository repository;
	
	@Autowired
	private ProductRepository repositoryProduct;
	
	@Autowired
	private OrderRepository repositoryOrder;

	public ItemOrderDTO create(ItemOrder itemOrder) {
		Integer orderID = itemOrder.getOrder().getId();
		Optional<Order> order = repositoryOrder.findById(orderID);
		
		Integer productID = itemOrder.getProduct().getId();
		Optional<Product> product = repositoryProduct.findById(productID);	
		
		if(!order.isPresent() || !product.isPresent()) {
			throw new ObjectNotFoundException("Comanda e/ou produto não existente");
		}
		
		ItemOrder newItem = repository.save(itemOrder);
		
		ItemOrderDTO dto = new ItemOrderDTO(newItem);
		
		return dto;
	}

	public void remove(Integer id) {
		Optional<ItemOrder> item = repository.findById(id);
		
		if(!item.isPresent()) {
			throw new ObjectNotFoundException(String.format("Item com id: %d não encontrado", id));
		}
		
		repository.deleteById(id);
	}
	
}
