package com.juniorsilvacc.fastservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorsilvacc.fastservice.domain.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

	Optional<Product> findByName(String name);
	
}
