package com.theophilusgordon.marketsquareserver.controllers;

import com.theophilusgordon.marketsquareserver.models.User;
import com.theophilusgordon.marketsquareserver.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @GetMapping("")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
