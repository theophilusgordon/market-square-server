package com.theophilusgordon.marketsquareserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequestDto {
    @NotNull(message = "Username is required")
    private String username;
    @NotNull(message = "Password is required")
    private String password;
}
