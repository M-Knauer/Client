package com.knauer.main.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.knauer.main.services.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("clients")
public class ClientController {

	@Autowired
	private ClientService service;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Page<ClientDTO> findAllPaged(Pageable pageable) {
		return service.findAll(pageable);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ClientDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}

	@PostMapping
	public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO dto) {
		dto = service.save(dto);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
			
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ClientDTO update(@PathVariable Long id, @Valid @RequestBody ClientDTO entity) {
		return service.update(id, entity);
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

}
