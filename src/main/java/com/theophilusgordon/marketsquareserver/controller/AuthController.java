package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.dto.AuthRequestDto;
import com.theophilusgordon.marketsquareserver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @ResponseStatus(HttpStatus.OK)
     @PostMapping("/login")
     public String login(@RequestBody AuthRequestDto authRequestDto){
         return authService.authenticate(authRequestDto);
     }
}
