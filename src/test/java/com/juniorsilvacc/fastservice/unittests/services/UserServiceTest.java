package com.juniorsilvacc.fastservice.unittests.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.juniorsilvacc.fastservice.domain.dtos.UserDTO;
import com.juniorsilvacc.fastservice.domain.entities.Permission;
import com.juniorsilvacc.fastservice.domain.entities.User;
import com.juniorsilvacc.fastservice.repositories.UserRepository;
import com.juniorsilvacc.fastservice.services.UserService;

@SpringBootTest
class UserServiceTest {
	
	private static final Integer ID = 1;
	private static final String USER_NAME = "Júniior Silva";
	private static final String EMAIL = "juniorsilvacc@hotmail.com";
	private static final String CPF = "945.616.520-51";
	private static final String AVATAR = null;
	private static final String PASSWORD = "123123";
	private List<Permission> PERMISSION;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserService service;
    
    @Mock
	private UserRepository repository;
	
	private User user;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		inputUser();
	}

	@Test
	void whenCreatedUserReturnsSuccessfully() {
		User persisted = new User(ID, USER_NAME, EMAIL, CPF, AVATAR, PASSWORD, PERMISSION);
		
		UserDTO response = service.create(persisted);

		response.setPassword(passwordEncoder.encode(PASSWORD));
		
	    assertNotNull(response);
	    assertNotNull(response.getId());
	    
	    assertEquals(UserDTO.class, response.getClass());
	    
	    assertEquals(ID, response.getId());
	    assertEquals(USER_NAME, response.getUser_name());
	    assertEquals(EMAIL, response.getEmail());
	    assertEquals(CPF, response.getCpf());
	    assertEquals(AVATAR, response.getAvatar());
	}
	
	@Test
	void whenUpdatingUserReturnsSuccessfully() {
		User persisted = new User(ID, USER_NAME, EMAIL, CPF, AVATAR, PASSWORD, PERMISSION);
		
		MockMultipartFile multipartFile = new MockMultipartFile("file",
                "fresh-pepper.jpg",
                "image/png, image/jpg, image/jpeg",
                "random image".getBytes());
		
		when(repository.findById(ID)).thenReturn(Optional.of(user));
		
		UserDTO response = service.uploadAvatar(persisted, multipartFile);
		response.setAvatar("fresh-pepper.jpg");
		
	    assertNotNull(response);
	    assertNotNull(response.getId());
	    
	    assertEquals(UserDTO.class, response.getClass());
	    
	    assertEquals(ID, response.getId());
	    assertEquals("fresh-pepper.jpg", response.getAvatar());
	}
	
	private void inputUser() {
		user = new User(ID, USER_NAME, EMAIL, CPF, AVATAR, PASSWORD, PERMISSION);
	}

}
