package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.AuthRequestDto;
import com.theophilusgordon.marketsquareserver.utils.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Override
    public String authenticate(AuthRequestDto authRequestDto) {
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtUtils.generateToken(authRequestDto.getUsername());
        }

        throw new UsernameNotFoundException("invalid user");
    }
}
