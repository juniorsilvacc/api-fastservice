package com.juniorsilvacc.fastservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.juniorsilvacc.fastservice.domain.dtos.security.AccountCredentialsDTO;
import com.juniorsilvacc.fastservice.domain.dtos.security.TokenDTO;
import com.juniorsilvacc.fastservice.repositories.UserRepository;
import com.juniorsilvacc.fastservice.security.JwtTokenProvider;

@Service
public class AuthService {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserRepository repository;
	
	public ResponseEntity<?> signin(AccountCredentialsDTO data) {
		try {
			var email = data.getEmail();
			var password = data.getPassword();
			
			// Tenta fazer o loguin com as credenciais obtidas
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, password));
			
			// Acessa o repositório e busca pelo email autenticado
			var user = repository.findByUseremail(email);
			
			var tokenResponse = new TokenDTO();
			
			// Se retornar algum email/usuário e for diferente de null, então cria um token passando o email e permissões
			if(user != null) {
				tokenResponse = tokenProvider.createAccessToken(email, user.getRoles());
			} else {
				throw new UsernameNotFoundException("E-mail " + email + " not found");
			}
			
			// Caso de sucesso, retorna um respondeEntity com um tokenResponse
			return ResponseEntity.ok(tokenResponse);
			
		} catch (Exception e) {
			throw new BadCredentialsException("Invalid e-mail/password suppliend");
		}
	}

}
