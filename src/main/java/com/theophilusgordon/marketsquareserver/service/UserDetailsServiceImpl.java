package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.converter.UserUserDetails;
import com.theophilusgordon.marketsquareserver.exception.UserException;
import com.theophilusgordon.marketsquareserver.model.User;
import com.theophilusgordon.marketsquareserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UserException {
        Optional<User> userInfo = userRepository.findByEmail(username);
        return userInfo.map(UserUserDetails::new)
            .orElseThrow(() -> new UserException("User not found"));
    }
}
