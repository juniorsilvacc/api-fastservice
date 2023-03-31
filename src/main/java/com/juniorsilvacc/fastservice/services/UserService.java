package com.juniorsilvacc.fastservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.domain.dtos.UserDTO;
import com.juniorsilvacc.fastservice.domain.entities.Permission;
import com.juniorsilvacc.fastservice.domain.entities.User;
import com.juniorsilvacc.fastservice.repositories.PermissionRepository;
import com.juniorsilvacc.fastservice.repositories.UserRepository;
import com.juniorsilvacc.fastservice.services.exceptions.MethodArgumentNotValidException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PermissionRepository permission;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserDTO create(User user) {
		Optional<User> email = repository.findByEmail(user.getEmail());
		Optional<User> cpf = repository.findByCpf(user.getCpf());
		Permission role = permission.findByDescription("ROLE_WAITER");

		if(email.isPresent()) {
			throw new MethodArgumentNotValidException("O e-mail já existe");
		}
		
		if(cpf.isPresent()) {
			throw new MethodArgumentNotValidException("O CPF já existe");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.getPermissions().add(role);
		
		User newUser = repository.save(user);
		
		UserDTO dto = new UserDTO(newUser);
		
		return dto;
	}

}
