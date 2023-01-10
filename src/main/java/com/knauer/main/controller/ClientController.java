package com.knauer.main.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(code = HttpStatus.OK)
	public List<ClientDTO> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
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
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ClientDTO update(@PathVariable Long id, @RequestBody ClientDTO dto) {
		return service.update(id, dto);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
}
