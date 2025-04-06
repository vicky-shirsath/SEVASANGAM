package com.sevasangam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "campaigns")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private NGO ngo;
    
    private String title;
    private String description;
    private Double targetAmount;
    private Double collectedAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String campaignImage;
    private String campaignStatus; // ACTIVE, COMPLETED, CANCELLED
    private String category; // Education, Health, Environment, etc.
}

