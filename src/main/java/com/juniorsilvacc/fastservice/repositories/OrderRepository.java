package com.juniorsilvacc.fastservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorsilvacc.fastservice.domain.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	Optional<Order> findByTable(int table);

}
