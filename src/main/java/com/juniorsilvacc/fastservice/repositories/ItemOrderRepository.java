package com.juniorsilvacc.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorsilvacc.fastservice.domain.entities.ItemOrder;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {

}
