package com.juniorsilvacc.fastservice.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	
	public InvalidAuthenticationException(String message) {
		super(message);
	}
	
}
