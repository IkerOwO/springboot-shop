package com.iker.shopBackend.services.Users;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iker.shopBackend.entities.User;
import com.iker.shopBackend.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }
    
    @Transactional(readOnly = true)
    public List<User> getAllClients() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }
    
    @Transactional
    public void updatePassword(Long id, String newPass) {
        Optional<User> opUser = repository.findById(id);
        if (opUser.isPresent()) {
            User user = opUser.get();
            user.setPassword(encoder.encode(newPass));
            repository.save(user);
        }
    }

    @Transactional
    public void deleteClient(Long id) {
        Optional<User> opUser = repository.findById(id);
        opUser.ifPresentOrElse(
            u -> repository.delete(u), 
            () -> System.out.println("Usuario no encontrado")
        );
    }
}
