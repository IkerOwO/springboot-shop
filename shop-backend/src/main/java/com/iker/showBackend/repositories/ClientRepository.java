package com.iker.showBackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iker.showBackend.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    
    List<Client> findAll();

    Optional<Client> findById(Long id); 
}
