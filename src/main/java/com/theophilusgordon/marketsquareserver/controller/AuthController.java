package com.theophilusgordon.marketsquareserver.controller;

import com.theophilusgordon.marketsquareserver.dto.AuthRequestDto;
import com.theophilusgordon.marketsquareserver.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthRequestDto authRequestDto){
        String token = authService.authenticate(authRequestDto);
        return ResponseEntity.ok(token);
    }
}
