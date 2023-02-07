package com.theophilusgordon.marketsquareserver.services;

import com.theophilusgordon.marketsquareserver.entities.UserEntity;
import com.theophilusgordon.marketsquareserver.models.User;
import com.theophilusgordon.marketsquareserver.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
