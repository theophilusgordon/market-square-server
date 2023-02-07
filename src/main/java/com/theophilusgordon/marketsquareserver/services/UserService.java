package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.models.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
}
