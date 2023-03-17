package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.dto.UserDto;
import com.theophilusgordon.marketsquareserver.model.User;
import com.theophilusgordon.marketsquareserver.service.UserService;
import com.theophilusgordon.marketsquareserver.utils.mapper.EntityObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final EntityObjectMapper entityObjectMapper;

    public UserController(UserService userService, EntityObjectMapper entityObjectMapper) {
        this.userService = userService;
        this.entityObjectMapper = entityObjectMapper;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public UserDto createUser(@RequestBody UserDto userDto){
        User createUser = userService.createUser(userDto);
        return entityObjectMapper.convertToUserDto(createUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public UserDto loginUser(@RequestBody User user){
        User loginUser = userService.loginUser(user);
        return entityObjectMapper.convertToUserDto(loginUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<UserDto> getAllUsers(){
        List<User> userEntities = userService.getAllUsers();
        return entityObjectMapper.convertToUserDtoList(userEntities);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Optional<UserDto> getUserById(@PathVariable UUID id){
        if(id == null)
            throw new RuntimeException("User id is required"
                    + " to get user details");

        Optional<User> user = userService.getUserById(id);

        if(!user.isPresent())
            throw new RuntimeException("User not found with id: " + id);

        return entityObjectMapper.convertToUserDtoOptional(user.get());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable UUID id, @RequestBody UserDto userDto){
        User updatedUser = userService.updateUser(id, userDto);
        return entityObjectMapper.convertToUserDto(updatedUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        userService.deleteUser(id);
    }
}
