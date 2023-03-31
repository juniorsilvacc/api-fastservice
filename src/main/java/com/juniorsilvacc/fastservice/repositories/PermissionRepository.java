package com.juniorsilvacc.fastservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juniorsilvacc.fastservice.domain.entities.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{
	
	Permission findByDescription(String description);

}
