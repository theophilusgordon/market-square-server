package com.theophilusgordon.marketsquareserver.service;

import com.theophilusgordon.marketsquareserver.dto.AuthRequestDto;

public interface AuthService {
    String authenticate(AuthRequestDto authRequestDto);
}
