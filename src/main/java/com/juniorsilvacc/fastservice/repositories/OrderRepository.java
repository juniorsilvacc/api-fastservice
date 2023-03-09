package com.juniorsilvacc.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorsilvacc.fastservice.domain.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
