package com.juniorsilvacc.fastservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.juniorsilvacc.fastservice.domain.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	@Query("SELECT u FROM User u WHERE u.email =:email")
	User findByUserEmail(@Param("email") String email);
	
	Optional<User> findByEmail(String email);
	
}
