package com.theophilusgordon.marketsquareserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @NotNull(message = "Password is required")
    @Length(min = 8, message = "Password must be at least 8 characters")
    public String password;
    public String role;
    public String phoneNumber;
    public String address;
    public String city;
    public String state;
    public String country;
}
