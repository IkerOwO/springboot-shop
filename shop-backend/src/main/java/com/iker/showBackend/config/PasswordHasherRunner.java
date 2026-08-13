package com.iker.showBackend.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.iker.showBackend.entities.User;
import com.iker.showBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component
public class PasswordHasherRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public PasswordHasherRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<User> users = userRepository.findAll();
        for (User u : users) {
            String pw = u.getPassword();
            if (pw == null) continue;
            if (!(pw.startsWith("$2a$") || pw.startsWith("$2b$") || pw.startsWith("$2y$"))) {
                String hashed = passwordEncoder.encode(pw);
                u.setPassword(hashed);
                userRepository.save(u);
                System.out.println("Hashed password for user id=" + u.getId() + " email=" + u.getEmail());
            }
        }
    }
}
