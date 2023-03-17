package com.theophilusgordon.marketsquareserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.theophilusgordon.marketsquareserver.model.enums.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Entity(name = "users")
@Data
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType role;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;
}
