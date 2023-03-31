package com.juniorsilvacc.fastservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.juniorsilvacc.fastservice.domain.dtos.UserDTO;
import com.juniorsilvacc.fastservice.domain.entities.Permission;
import com.juniorsilvacc.fastservice.domain.entities.User;
import com.juniorsilvacc.fastservice.repositories.PermissionRepository;
import com.juniorsilvacc.fastservice.repositories.UserRepository;
import com.juniorsilvacc.fastservice.services.exceptions.MethodArgumentNotValidException;
import com.juniorsilvacc.fastservice.services.exceptions.ResourceNotFoundException;
import com.juniorsilvacc.fastservice.util.UploadUtil;

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

	public UserDTO uploadAvatar(User user, MultipartFile image) {
		Optional<User> entity = repository.findById(user.getId());
		
		if(!entity.isPresent()) {
			throw new ResourceNotFoundException("Usuário não encontrado, somente usuários autenticados podem alterar o avatar");
		}
		
		if(UploadUtil.uploadImage(image)) {
			entity.get().setAvatar(image.getOriginalFilename());
			
		}
		
		var uploadAvatar = repository.save(entity.get());
		
		UserDTO dto = new UserDTO(uploadAvatar);
		
		return dto;
	}

}
