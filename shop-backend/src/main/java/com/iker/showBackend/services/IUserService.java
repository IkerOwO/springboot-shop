package com.iker.showBackend.services;

import java.util.List;
import java.util.Optional;
import com.iker.showBackend.entities.User;

public interface IUserService {
    List<User> getAllClients();

    Optional<User> getById(Long id);

    void updatePassword(Long id, String newPass);

    void deleteClient(Long id);
}
