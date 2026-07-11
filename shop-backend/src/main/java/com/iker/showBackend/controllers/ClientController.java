package com.iker.showBackend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iker.showBackend.entities.Client;
import com.iker.showBackend.services.ClientService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/getClients")
    public List<Client> getAllClients() {
        return service.getAllClients();
    }
    
    @GetMapping("/{id}")
    public Optional<Client> getById(@RequestParam Long id) {
        return service.getById(id);
    }
    
    @PostMapping("/createClient")
    public void createClient(@RequestBody Client client) {
        service.createClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@RequestParam Long id){
        service.deleteClient(id);
    }

    @PutMapping("/{id}")
    public void updatePassword(@PathVariable Client client, @RequestBody String newPass) {
        service.updatePassword(client, newPass);
    }
    
}
