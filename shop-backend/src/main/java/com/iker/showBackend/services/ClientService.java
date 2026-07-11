package com.iker.showBackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iker.showBackend.entities.Client;
import com.iker.showBackend.repositories.ClientRepository;

@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Client> getAllClients(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Client> getById(Long id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Client> login(String email, String password) {
        return repository.login(email, password);
    }

    @Transactional
    public void createClient(Client client){
        Optional<Client> opClient = repository.findById(client.getId());
        if (opClient.isPresent()){
            throw new IllegalStateException("Client already exists");
        }
        repository.save(client);
    }

    @Transactional
    public void deleteClient(Long id){
        Optional<Client> opClient = repository.findById(id);
        opClient.ifPresentOrElse(
            client -> repository.delete(client), 
            () -> System.out.println("Client not found!")
        );
    }

    @Transactional
    public void updatePassword(Client client, String newPass){
        Optional<Client> opClient = repository.findById(client.getId());
        if (opClient.isPresent()){
            client.setClientPassword(newPass);
        }
        repository.save(client);
    }
}
