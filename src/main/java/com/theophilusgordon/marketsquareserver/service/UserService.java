package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.UserDto;
import com.theophilusgordon.marketsquareserver.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createUser(UserDto userDto);
    User loginUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(UUID id);
    User updateUser(UUID id, UserDto userDto);
    void deleteUser(UUID id);
    User getUserByEmail(String email);

}
