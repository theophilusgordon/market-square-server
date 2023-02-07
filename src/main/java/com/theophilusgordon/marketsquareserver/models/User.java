package com.theophilusgordon.marketsquareserver.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String country;

}
