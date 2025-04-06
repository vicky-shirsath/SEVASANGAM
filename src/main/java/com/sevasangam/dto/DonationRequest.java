package com.sevasangam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonationRequest {
    private Long donorId;
    private Long ngoId;
    private Long campaignId;
    private Double amount;
    private String paymentId;
    private String donationType;
    private String message;
}

