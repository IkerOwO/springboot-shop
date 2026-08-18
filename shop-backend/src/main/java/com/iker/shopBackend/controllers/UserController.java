package com.iker.shopBackend.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iker.shopBackend.entities.User;
import com.iker.shopBackend.services.Users.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/getClients")
    public List<User> getAllClients() {
        return service.getAllClients();
    }
    
    @GetMapping("/{id}")
    public Optional<User> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}/password")
    public void updatePassword(@Valid  @RequestBody String newPass, @PathVariable Long id) {
        service.updatePassword(id, newPass);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(Long id) {
        service.deleteClient(id);
    }
}
