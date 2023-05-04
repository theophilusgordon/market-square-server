package com.theophilusgordon.marketsquareserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.theophilusgordon.marketsquareserver.model.enums.UserRoles;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;
import java.util.UUID;

@Entity(name = "users")
@Data
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @Email
    @NotNull(message = "Email is required")
    private String email;
    @NotNull(message = "Password is required")
    @Length(min = 8, message = "Password must be at least 8 characters")
    private String password;
    @ElementCollection(targetClass = UserRoles.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRoles> roles;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;
}
