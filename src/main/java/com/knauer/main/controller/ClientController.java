package com.knauer.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knauer.main.entities.Client;
import com.knauer.main.services.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping
	public List<Client> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Client findById(@PathVariable Long id) {
		return service.findById(id);
	}
}
