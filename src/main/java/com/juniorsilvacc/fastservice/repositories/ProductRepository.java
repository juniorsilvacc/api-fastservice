package com.juniorsilvacc.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorsilvacc.fastservice.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
