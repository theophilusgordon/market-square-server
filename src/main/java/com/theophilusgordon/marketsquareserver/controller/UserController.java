package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.dto.UserDto;
import com.theophilusgordon.marketsquareserver.entity.User;
import com.theophilusgordon.marketsquareserver.service.UserService;
import com.theophilusgordon.marketsquareserver.utils.mapper.EntityObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final EntityObjectMapper entityObjectMapper;

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User createUser = userService.createUser(userDto);
        UserDto createdUserDto = entityObjectMapper.convertToUserDto(createUser);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> userEntities = userService.getAllUsers();
        List<UserDto> userDtoList = entityObjectMapper.convertToUserDtoList(userEntities);
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        UserDto userDto = entityObjectMapper.convertToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userDto);
        UserDto updatedUserDto = entityObjectMapper.convertToUserDto(updatedUser);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
