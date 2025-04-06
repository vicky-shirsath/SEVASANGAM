package com.sevasangam.controller;

import com.sevasangam.dto.DonationRequest;
import com.sevasangam.model.Donation;
import com.sevasangam.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private DonationService donationService;
    
    @PostMapping
    public ResponseEntity<Donation> makeDonation(@RequestBody DonationRequest donationRequest) {
        Donation donation = donationService.makeDonation(donationRequest);
        return ResponseEntity.ok(donation);
    }
    
    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<Donation>> getDonationsByDonor(@PathVariable Long donorId) {
        List<Donation> donations = donationService.getDonationsByDonor(donorId);
        return ResponseEntity.ok(donations);
    }
    
    @GetMapping("/ngo/{ngoId}")
    public ResponseEntity<List<Donation>> getDonationsByNGO(@PathVariable Long ngoId) {
        List<Donation> donations = donationService.getDonationsByNGO(ngoId);
        return ResponseEntity.ok(donations);
    }
    
    @GetMapping("/campaign/{campaignId}")
    public ResponseEntity<List<Donation>> getDonationsByCampaign(@PathVariable Long campaignId) {
        List<Donation> donations = donationService.getDonationsByCampaign(campaignId);
        return ResponseEntity.ok(donations);
    }
}

