package com.juniorsilvacc.fastservice.exceptions.handle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.juniorsilvacc.fastservice.exceptions.StandardError;
import com.juniorsilvacc.fastservice.exceptions.ValidationError;
import com.juniorsilvacc.fastservice.services.exceptions.InvalidAuthenticationException;
import com.juniorsilvacc.fastservice.services.exceptions.DataIntegrityViolationException;
import com.juniorsilvacc.fastservice.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<StandardError> handleAllExceptions(
			Exception ex, HttpServletRequest  request) {
		
		StandardError exceptionResponse = new StandardError(System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Internal Server Error", ex.getMessage(), request.getRequestURI());
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public final ResponseEntity<StandardError> objectNotFoundException(
			Exception ex, HttpServletRequest request) {
		
		StandardError exceptionResponse = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Object Not Found", ex.getMessage(), request.getRequestURI());
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public final ResponseEntity<StandardError> dataIntegrityViolationException(
			Exception ex, HttpServletRequest request) {
		StandardError exceptionResponse = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Data Integrity Violation", ex.getMessage(), request.getRequestURI());
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidAuthenticationException.class)
	public final ResponseEntity<StandardError> invalidAuthenticationException(
			Exception ex, HttpServletRequest request) {
		StandardError exceptionResponse = new StandardError(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(),
				"Invalid Autentication", ex.getMessage(), request.getRequestURI());
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> validationErros(MethodArgumentNotValidException ex, HttpServletRequest request) {
		
		ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Validation Error", "Erro na validação dos campos", request.getRequestURI());
				
				for(FieldError x : ex.getBindingResult().getFieldErrors()) {
					errors.addError(x.getField(), x.getDefaultMessage());
				}
		
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
		
}
