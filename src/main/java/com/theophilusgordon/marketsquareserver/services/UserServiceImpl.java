package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.Exceptions.UserNotFoundException;
import com.theophilusgordon.marketsquareserver.entities.UserEntity;
import com.theophilusgordon.marketsquareserver.models.User;
import com.theophilusgordon.marketsquareserver.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userEntity -> {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            return user;
        }).toList();
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).map(userEntity -> {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            return user;
        }).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public User updateUser(User user) {
        return userRepository.findById(user.getId()).map(userEntity -> {
            BeanUtils.copyProperties(user, userEntity);
            userRepository.save(userEntity);
            return user;
        }).orElseThrow(() -> new UserNotFoundException("User not found with id: " + user.getId()));
    }

    @Override
    public void deleteUser(UUID id) {
        boolean userExists = userRepository.existsById(id);
        if(!userExists){
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
