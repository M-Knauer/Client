package com.knauer.main.controller.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.knauer.main.services.exceptions.ControllerNotFoundException;
import com.knauer.main.services.exceptions.DatabaseException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ControllerNotFoundException.class)
	public ResponseEntity<StandarError> entityNotFound(ControllerNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandarError err = new StandarError(
				Instant.now(),
				status.value(),
				"Controller not found",
				e.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandarError> database(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandarError err = new StandarError(
				Instant.now(),
				status.value(),
				"Database exception",
				e.getMessage(),
				request.getRequestURI()
				);
		
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandarError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		ValidationError err = new ValidationError(
				Instant.now(),
				status.value(),
				"Valor inválido",
				"Error",
				request.getRequestURI()
				);
		
		for (FieldError f : e.getBindingResult().getFieldErrors()) {
			err.addMessages(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(err);
	}
}
