package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.UserDto;
import com.theophilusgordon.marketsquareserver.exception.UserException;
import com.theophilusgordon.marketsquareserver.entity.User;
import com.theophilusgordon.marketsquareserver.entity.enums.UserRoles;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(10);
    }

    @Override
    public User createUser(UserDto userDto) {
        userRepository.findByEmail(userDto.getEmail())
            .ifPresent(existingUser -> {
                throw new UserException("Email already in use: " + userDto.getEmail());
            });

        User userEntity = new User();
        BeanUtils.copyProperties(userDto, userEntity);
        userType(userDto, userEntity);
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public User loginUser(User user) {
        return userRepository.findByEmail(user.getEmail())
            .filter(userEntity -> passwordEncoder.matches(user.getPassword(), userEntity.getPassword()))
            .orElseThrow(() -> new UserException("Invalid email or password"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(UUID id, UserDto userDto) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> new UserException("User not found with id: " + id));
        userType(userDto, userEntity);
        BeanUtils.copyProperties(userDto, userEntity, "password");
        if (userDto.getPassword() != null) {
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public void deleteUser(UUID id) {
        User userEntity = userRepository.findById(id).orElseThrow(() -> new UserException("User not found with id: " + id));
        userRepository.delete(userEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found with email: " + email));
    }

    private static void userType(UserDto userDto, User userEntity) {
        if (userDto.getRole() != null) {
            switch (userDto.getRole()) {
                case "admin" -> userEntity.setRoles(Collections.singleton(UserRoles.ADMIN));
                case "seller" -> userEntity.setRoles(Collections.singleton(UserRoles.SELLER));
                case "buyer" -> userEntity.setRoles(Collections.singleton(UserRoles.BUYER));
                default -> throw new UserException("Invalid user role");
            }
        }
    }
}
