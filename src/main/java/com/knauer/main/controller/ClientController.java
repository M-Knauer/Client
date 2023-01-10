package com.knauer.main.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.knauer.main.dto.ClientDTO;
import com.knauer.main.entities.Client;
import com.knauer.main.services.ClientService;

@RestController
@RequestMapping("clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@GetMapping
	public List<ClientDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public ClientDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> save(@RequestBody Client entity) {
		ClientDTO dto = service.save(entity);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
			
		return ResponseEntity.created(uri).body(dto);
	}
	
}
