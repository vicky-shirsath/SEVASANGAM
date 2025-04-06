package com.sevasangam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ngos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NGO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String ngoName;
    private String ngoDescription;
    private String ngoRegistrationNumber;
    private String ngoRegistrationCertificate;
    private String ngoLogo;
    private String ngoWebsite;
    private String ngoType; // Education, Health, Environment, etc.
    private boolean isVerified;
}

