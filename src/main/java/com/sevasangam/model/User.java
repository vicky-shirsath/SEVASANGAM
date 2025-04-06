package com.sevasangam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;
    private String password;
    private String role; // DONOR, NGO, ADMIN
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String profileImage;
    private boolean isVerified;
    private String verificationToken;
}

