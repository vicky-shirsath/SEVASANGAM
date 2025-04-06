package com.sevasangam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
}

