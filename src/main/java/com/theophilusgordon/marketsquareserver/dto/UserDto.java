package com.theophilusgordon.marketsquareserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    public String id;
    @NotNull
    public String firstName;
    @NotNull
    public String lastName;
    @NotNull
    public String email;
    public String phoneNumber;
    public String address;
    public String city;
    public String state;
    public String country;
}
