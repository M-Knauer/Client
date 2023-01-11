package com.knauer.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.knauer.main.dto.ClientDTO;
import com.knauer.main.entities.Client;
import com.knauer.main.repositories.ClientRepository;
import com.knauer.main.services.exceptions.ControllerNotFoundException;
import com.knauer.main.services.exceptions.DatabaseException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAll(Pageable pageable) {
		
		return repository.findAll(pageable).map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		return new ClientDTO(repository.findById(id)
				.orElseThrow(() -> new ControllerNotFoundException("ID not found: "+id)));
	}

	@Transactional
	public ClientDTO save(ClientDTO dto) {
		Client entity = new Client();
		
		toEntity(dto, entity);
		
		dto = new ClientDTO(repository.save(entity));
		
		return dto;
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
		Client entity = repository.getReferenceById(id);
		
		toEntity(dto, entity);
		
		dto = new ClientDTO(repository.save(entity));
		
		return dto;
		
		} catch (EntityNotFoundException e) {
			throw new ControllerNotFoundException("ID not found: "+id);
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new ControllerNotFoundException("ID not found: "+id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}
	
	private void toEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setBirthDate(dto.getBirthDate());
		entity.setIncome(dto.getIncome());
		entity.setChildren(dto.getChildren());
	
	}
	
}
