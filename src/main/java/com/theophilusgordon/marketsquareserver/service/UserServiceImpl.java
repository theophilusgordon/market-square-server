package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.Exceptions.UserException;
import com.theophilusgordon.marketsquareserver.model.User;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user) {
        User userEntity = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        BeanUtils.copyProperties(user, userEntity);
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public User loginUser(User user) {
        User userEntity = userRepository.findByEmail(user.getEmail());
        if(userEntity == null){
            throw new UserException("User not found with email: " + user.getEmail());
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        if(!passwordEncoder.matches(user.getPassword(), userEntity.getPassword())){
            throw new UserException("Password is incorrect");
        }

        return userEntity;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userEntities = (List<User>) userRepository.findAll();
        return userEntities.stream().map(userEntity -> {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            return user;
        }).toList();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return Optional.ofNullable(userRepository.findById(id).map(userEntity -> {
            User user = new User();
            BeanUtils.copyProperties(userEntity, user);
            return user;
        }).orElseThrow(() -> new UserException("User not found with id: " + id)));
    }

    @Override
    public User updateUser(UUID id, User user) {
        boolean userExists = userRepository.existsById(id);
        if(!userExists){
            throw new UserException("User not found with id: " + id);
        }

        if(user.getPassword() != null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        User userEntity = userRepository.findById(id).get();
        if(user.getFirstName() != null){
            userEntity.setFirstName(user.getFirstName());
        }

        if(user.getLastName() != null){
            userEntity.setLastName(user.getLastName());
        }

        if(user.getEmail() != null){
            userEntity.setEmail(user.getEmail());
        }

        if(user.getPhoneNumber() != null){
            userEntity.setPhoneNumber(user.getPhoneNumber());
        }

        if(user.getPassword() != null){
            userEntity.setPassword(user.getPassword());
        }

        if(user.getAddress() != null){
            userEntity.setAddress(user.getAddress());
        }

        if(user.getCity() != null){
            userEntity.setCity(user.getCity());
        }

        if(user.getState() != null){
            userEntity.setState(user.getState());
        }

        if(user.getCountry() != null){
            userEntity.setCountry(user.getCountry());
        }

        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public void deleteUser(UUID id) {
        boolean userExists = userRepository.existsById(id);
        if(!userExists){
            throw new UserException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
