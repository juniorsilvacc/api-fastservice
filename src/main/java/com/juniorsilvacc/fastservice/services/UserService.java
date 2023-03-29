package com.juniorsilvacc.fastservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.domain.dtos.UserDTO;
import com.juniorsilvacc.fastservice.domain.entities.User;
import com.juniorsilvacc.fastservice.repositories.UserRepository;
import com.juniorsilvacc.fastservice.services.exceptions.MethodArgumentNotValidException;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		var user = repository.findByUserEmail(email);
		
		if(user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Email: " + email + " não encontrado");
		}
		
	}

	public UserDTO create(User user) {
		Optional<User> entity = repository.findByEmail(user.getEmail());
		
		if(entity.isPresent()) {
			throw new MethodArgumentNotValidException("Esse e-mail já existe");
		}
		
		User newUser = repository.save(user);
		
		UserDTO dto = new UserDTO(newUser);
		
		return dto;
	}
	
}
