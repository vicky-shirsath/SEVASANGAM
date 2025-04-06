package com.sevasangam.service;

import com.sevasangam.dto.CampaignDTO;
import com.sevasangam.model.Campaign;
import com.sevasangam.model.NGO;
import com.sevasangam.repository.CampaignRepository;
import com.sevasangam.repository.NGORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;
    
    @Autowired
    private NGORepository ngoRepository;
    
    public CampaignDTO createCampaign(CampaignDTO campaignDTO) {
        Optional<NGO> ngoOptional = ngoRepository.findById(campaignDTO.getNgoId());
        
        if (ngoOptional.isEmpty()) {
            throw new RuntimeException("NGO not found");
        }
        
        NGO ngo = ngoOptional.get();
        
        Campaign campaign = new Campaign();
        campaign.setNgo(ngo);
        campaign.setTitle(campaignDTO.getTitle());
        campaign.setDescription(campaignDTO.getDescription());
        campaign.setTargetAmount(campaignDTO.getTargetAmount());
        campaign.setCollectedAmount(0.0);
        campaign.setStartDate(LocalDateTime.now());
        campaign.setEndDate(campaignDTO.getEndDate());
        campaign.setCampaignImage(campaignDTO.getCampaignImage());
        campaign.setCampaignStatus("ACTIVE");
        campaign.setCategory(campaignDTO.getCategory());
        
        Campaign savedCampaign = campaignRepository.save(campaign);
        
        return convertToDTO(savedCampaign);
    }
    
    public CampaignDTO getCampaignById(Long id) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        
        if (campaignOptional.isEmpty()) {
            throw new RuntimeException("Campaign not found");
        }
        
        return convertToDTO(campaignOptional.get());
    }
    
    public List<CampaignDTO> getAllCampaigns() {
        return campaignRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CampaignDTO> getCampaignsByNGO(Long ngoId) {
        Optional<NGO> ngoOptional = ngoRepository.findById(ngoId);
        
        if (ngoOptional.isEmpty()) {
            throw new RuntimeException("NGO not found");
        }
        
        NGO ngo = ngoOptional.get();
        
        return campaignRepository.findByNgo(ngo).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CampaignDTO> getActiveCampaigns() {
        return campaignRepository.findByCampaignStatus("ACTIVE").stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public List<CampaignDTO> getCampaignsByCategory(String category) {
        return campaignRepository.findByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public CampaignDTO updateCampaign(Long id, CampaignDTO campaignDTO) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        
        if (campaignOptional.isEmpty()) {
            throw new RuntimeException("Campaign not found");
        }
        
        Campaign campaign = campaignOptional.get();
        campaign.setTitle(campaignDTO.getTitle());
        campaign.setDescription(campaignDTO.getDescription());
        campaign.setTargetAmount(campaignDTO.getTargetAmount());
        campaign.setEndDate(campaignDTO.getEndDate());
        campaign.setCampaignImage(campaignDTO.getCampaignImage());
        campaign.setCategory(campaignDTO.getCategory());
        
        Campaign updatedCampaign = campaignRepository.save(campaign);
        
        return convertToDTO(updatedCampaign);
    }
    
    public CampaignDTO updateCampaignStatus(Long id, String status) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        
        if (campaignOptional.isEmpty()) {
            throw new RuntimeException("Campaign not found");
        }
        
        Campaign campaign = campaignOptional.get();
        campaign.setCampaignStatus(status);
        
        Campaign updatedCampaign = campaignRepository.save(campaign);
        
        return convertToDTO(updatedCampaign);
    }
    
    public void deleteCampaign(Long id) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(id);
        
        if (campaignOptional.isEmpty()) {
            throw new RuntimeException("Campaign not found");
        }
        
        campaignRepository.deleteById(id);
    }
    
    private CampaignDTO convertToDTO(Campaign campaign) {
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.setId(campaign.getId());
        campaignDTO.setNgoId(campaign.getNgo().getId());
        campaignDTO.setNgoName(campaign.getNgo().getNgoName());
        campaignDTO.setTitle(campaign.getTitle());
        campaignDTO.setDescription(campaign.getDescription());
        campaignDTO.setTargetAmount(campaign.getTargetAmount());
        campaignDTO.setCollectedAmount(campaign.getCollectedAmount());
        campaignDTO.setStartDate(campaign.getStartDate());
        campaignDTO.setEndDate(campaign.getEndDate());
        campaignDTO.setCampaignImage(campaign.getCampaignImage());
        campaignDTO.setCampaignStatus(campaign.getCampaignStatus());
        campaignDTO.setCategory(campaign.getCategory());
        
        return campaignDTO;
    }
}

