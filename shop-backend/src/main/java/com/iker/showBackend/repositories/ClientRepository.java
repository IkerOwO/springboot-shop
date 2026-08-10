package com.iker.showBackend.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import com.iker.showBackend.entities.User;

public interface ClientRepository extends JpaRepository<User, Long> {
    
    List<User> findAll();

    Optional<User> findById(Long id); 

    boolean existsByEmail(String email);

    @Query("SELECT c.Name FROM Client c WHERE clientEmail=?1 AND clientPassword=?2")
    ResponseEntity<?> login(String email, String password);
}
