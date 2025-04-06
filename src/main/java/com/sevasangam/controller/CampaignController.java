package com.sevasangam.controller;

import com.sevasangam.dto.CampaignDTO;
import com.sevasangam.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;
    
    @PostMapping
    public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CampaignDTO campaignDTO) {
        CampaignDTO createdCampaign = campaignService.createCampaign(campaignDTO);
        return ResponseEntity.ok(createdCampaign);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CampaignDTO> getCampaignById(@PathVariable Long id) {
        CampaignDTO campaignDTO = campaignService.getCampaignById(id);
        return ResponseEntity.ok(campaignDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<CampaignDTO>> getAllCampaigns() {
        List<CampaignDTO> campaigns = campaignService.getAllCampaigns();
        return ResponseEntity.ok(campaigns);
    }
    
    @GetMapping("/ngo/{ngoId}")
    public ResponseEntity<List<CampaignDTO>> getCampaignsByNGO(@PathVariable Long ngoId) {
        List<CampaignDTO> campaigns = campaignService.getCampaignsByNGO(ngoId);
        return ResponseEntity.ok(campaigns);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<CampaignDTO>> getActiveCampaigns() {
        List<CampaignDTO> campaigns = campaignService.getActiveCampaigns();
        return ResponseEntity.ok(campaigns);
    }
    
    @GetMapping("/category/{category}")
    public ResponseEntity<List<CampaignDTO>> getCampaignsByCategory(@PathVariable String category) {
        List<CampaignDTO> campaigns = campaignService.getCampaignsByCategory(category);
        return ResponseEntity.ok(campaigns);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CampaignDTO> updateCampaign(@PathVariable Long id, @RequestBody CampaignDTO campaignDTO) {
        CampaignDTO updatedCampaign = campaignService.updateCampaign(id, campaignDTO);
        return ResponseEntity.ok(updatedCampaign);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<CampaignDTO> updateCampaignStatus(@PathVariable Long id, @RequestParam String status) {
        CampaignDTO updatedCampaign = campaignService.updateCampaignStatus(id, status);
        return ResponseEntity.ok(updatedCampaign);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }
}

