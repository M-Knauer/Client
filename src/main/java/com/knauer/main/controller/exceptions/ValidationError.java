package com.knauer.main.controller.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError{
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> messages = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getMessages() {
		return messages;
	}

	public void addMessages(String fieldName, String message) {
		messages.add(new FieldMessage(fieldName, message));
	}
}
