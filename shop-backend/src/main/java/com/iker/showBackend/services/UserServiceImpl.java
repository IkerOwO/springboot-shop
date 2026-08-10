package com.iker.showBackend.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.iker.showBackend.entities.User;
import com.iker.showBackend.repositories.ClientRepository;

@Service
public class UserServiceImpl implements IUserService {
    
    @Autowired
    private ClientRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public UserServiceImpl(ClientRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllClients() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getById(Long id) {
        return repository.findById(id);
    }
    
    @Transactional
    @Override
    public void updatePassword(Long id, String newPass) {
        Optional<User> opUser = repository.findById(id);
        if (opUser.isPresent()) {
            User user = opUser.get();
            user.setPassword(encoder.encode(newPass));
            repository.save(user);
        }
    }

    @Transactional
    @Override
    public void deleteClient(Long id) {
        Optional<User> opUser = repository.findById(id);
        opUser.ifPresentOrElse(
            u -> repository.delete(u), 
            () -> System.out.println("Usuario no encontrado")
        );
    }
}
