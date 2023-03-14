package com.theophilusgordon.marketsquareserver.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;
    @OneToMany(mappedBy = "seller")
    private List<Product> products;
}
