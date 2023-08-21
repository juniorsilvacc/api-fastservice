package com.juniorsilvacc.fastservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorsilvacc.fastservice.domain.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findByName(String name);

}
