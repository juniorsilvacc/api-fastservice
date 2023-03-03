package com.juniorsilvacc.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorsilvacc.fastservice.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
