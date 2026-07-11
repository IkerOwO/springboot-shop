package com.iker.showBackend.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iker.showBackend.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long> {
    
    List<Client> findAll();

    Optional<Client> findById(Long id); 

    @Query("SELECT c.Name FROM Client c WHERE clientEmail=?1 AND clientPassword=?2")
    Optional<Client> login(String email, String password);
}
