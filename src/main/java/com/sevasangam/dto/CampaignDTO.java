package com.sevasangam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDTO {
    private Long id;
    private Long ngoId;
    private String ngoName;
    private String title;
    private String description;
    private Double targetAmount;
    private Double collectedAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String campaignImage;
    private String campaignStatus;
    private String category;
}

