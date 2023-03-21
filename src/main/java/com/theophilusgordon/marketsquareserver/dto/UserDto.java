package com.theophilusgordon.marketsquareserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class UserDto {
    public String id;
    @NotNull(message = "First name is required")
    public String firstName;
    @NotNull(message = "Last name is required")
    public String lastName;
    @NotNull(message = "Email is required")
    public String email;
    public String password;
    public String role;
    public String phoneNumber;
    public String address;
    public String city;
    public String state;
    public String country;
}
