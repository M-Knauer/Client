package com.knauer.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knauer.main.entities.Client;
import com.knauer.main.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public List<Client> findAll() {
		
		return repository.findAll();
	}

	@Transactional(readOnly = true)
	public Client findById(Long id) {
		
		return repository.findById(id).get();
	}
	
	
}