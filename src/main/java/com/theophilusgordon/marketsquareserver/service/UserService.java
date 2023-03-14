package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createUser(User user);

    User loginUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(UUID id);
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
}
