package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.UserDto;
import com.theophilusgordon.marketsquareserver.exception.UserException;
import com.theophilusgordon.marketsquareserver.model.User;
import com.theophilusgordon.marketsquareserver.model.enums.UserType;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import com.theophilusgordon.marketsquareserver.utils.mapper.EntityObjectMapper;
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
    public User createUser(UserDto userDto) {
        User userEntity = new User();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userType(userDto, userEntity);
        BeanUtils.copyProperties(userDto, userEntity);
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
    public User updateUser(UUID id, UserDto userDto) {
        boolean userExists = userRepository.existsById(id);
        if(!userExists){
            throw new UserException("User not found with id: " + id);
        }

        User userEntity = userRepository.findById(id).get();

        userType(userDto, userEntity);

        if(userDto.getPassword() != null){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
            userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        if(userDto.getFirstName() != null){
            userEntity.setFirstName(userDto.getFirstName());
        }

        if(userDto.getLastName() != null){
            userEntity.setLastName(userDto.getLastName());
        }

        if(userDto.getEmail() != null){
            userEntity.setEmail(userDto.getEmail());
        }

        if(userDto.getPhoneNumber() != null){
            userEntity.setPhoneNumber(userDto.getPhoneNumber());
        }

        if(userDto.getAddress() != null){
            userEntity.setAddress(userDto.getAddress());
        }

        if(userDto.getCity() != null){
            userEntity.setCity(userDto.getCity());
        }

        if(userDto.getState() != null){
            userEntity.setState(userDto.getState());
        }

        if(userDto.getCountry() != null){
            userEntity.setCountry(userDto.getCountry());
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
    private static void userType(UserDto userDto, User userEntity) {
        if(userDto.getRole() != null){
            if(userDto.getRole().equals("admin"))
                userEntity.setRole(UserType.ADMIN);
            else if(userDto.getRole().equals("seller"))
                userEntity.setRole(UserType.SELLER);
            else if(userDto.getRole().equals("buyer"))
                userEntity.setRole(UserType.BUYER);
            else throw new UserException("Invalid user role");
        }
    }
}
