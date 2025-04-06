package com.sevasangam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "donations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;
    
    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private NGO ngo;
    
    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;
    
    private Double amount;
    private String paymentId;
    private String paymentStatus;
    private LocalDateTime donationDate;
    private String donationType; // MONEY, GOODS, etc.
    private String message;
}

