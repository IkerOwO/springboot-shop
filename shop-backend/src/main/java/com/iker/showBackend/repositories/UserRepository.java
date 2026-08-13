package com.iker.showBackend.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.iker.showBackend.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findAll();

    Optional<User> findById(Long id); 

    boolean existsByEmail(String email);
}
