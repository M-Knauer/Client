package com.knauer.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knauer.main.dto.ClientDTO;
import com.knauer.main.entities.Client;
import com.knauer.main.repositories.ClientRepository;
import com.knauer.main.services.exceptions.ControllerNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public List<ClientDTO> findAll() {
		List<ClientDTO> dtos = repository.findAll().stream()
				.map(x -> new ClientDTO(x)).toList();
		
		return dtos;
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		return new ClientDTO(repository.findById(id)
				.orElseThrow(() -> new ControllerNotFoundException("ID not found: "+id)));
	}

	public ClientDTO save(Client entity) {
		ClientDTO dto = new ClientDTO(repository.save(entity));
		
		return dto;
	}
	
}
