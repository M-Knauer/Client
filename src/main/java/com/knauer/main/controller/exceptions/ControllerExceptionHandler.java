package com.knauer.main.controller.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.knauer.main.services.exceptions.ControllerNotFoundException;
import com.knauer.main.services.exceptions.DatabaseException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ControllerNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public StandarError entityNotFound(ControllerNotFoundException e, HttpServletRequest request) {
		StandarError err = new StandarError(
				Instant.now(),
				HttpStatus.NOT_FOUND.value(),
				"Controller not found",
				e.getMessage(),
				request.getRequestURI()
				);
		
		return err;
	}
	
	@ExceptionHandler(DatabaseException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public StandarError database(DatabaseException e, HttpServletRequest request) {
		StandarError err = new StandarError(
				Instant.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Controller not found",
				e.getMessage(),
				request.getRequestURI()
				);
		
		return err;
	}
}
