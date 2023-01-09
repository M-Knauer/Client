package com.knauer.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.knauer.main.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
