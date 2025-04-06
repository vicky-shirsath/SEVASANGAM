package com.sevasangam.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NGORegistrationRequest {
    private String ngoName;
    private String ngoDescription;
    private String ngoRegistrationNumber;
    private String ngoType;
    private String ngoWebsite;
}

