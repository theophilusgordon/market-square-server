package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(UUID id);
    User updateUser(User user);
    void deleteUser(UUID id);
}
