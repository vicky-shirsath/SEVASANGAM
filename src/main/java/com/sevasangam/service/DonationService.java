package com.sevasangam.service;

import com.sevasangam.dto.DonationRequest;
import com.sevasangam.model.Campaign;
import com.sevasangam.model.Donation;
import com.sevasangam.model.Donor;
import com.sevasangam.model.NGO;
import com.sevasangam.repository.CampaignRepository;
import com.sevasangam.repository.DonationRepository;
import com.sevasangam.repository.DonorRepository;
import com.sevasangam.repository.NGORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;
    
    @Autowired
    private DonorRepository donorRepository;
    
    @Autowired
    private NGORepository ngoRepository;
    
    @Autowired
    private CampaignRepository campaignRepository;
    
    public Donation makeDonation(DonationRequest donationRequest) {
        Optional<Donor> donorOptional = donorRepository.findById(donationRequest.getDonorId());
        Optional<NGO> ngoOptional = ngoRepository.findById(donationRequest.getNgoId());
        Optional<Campaign> campaignOptional = campaignRepository.findById(donationRequest.getCampaignId());
        
        if (donorOptional.isEmpty()) {
            throw new RuntimeException("Donor not found");
        }
        
        if (ngoOptional.isEmpty()) {
            throw new RuntimeException("NGO not found");
        }
        
        if (campaignOptional.isEmpty()) {
            throw new RuntimeException("Campaign not found");
        }
        
        Donor donor = donorOptional.get();
        NGO ngo = ngoOptional.get();
        Campaign campaign = campaignOptional.get();
        
        Donation donation = new Donation();
        donation.setDonor(donor);
        donation.setNgo(ngo);
        donation.setCampaign(campaign);
        donation.setAmount(donationRequest.getAmount());
        donation.setPaymentId(donationRequest.getPaymentId());
        donation.setPaymentStatus("COMPLETED");
        donation.setDonationDate(LocalDateTime.now());
        donation.setDonationType(donationRequest.getDonationType());
        donation.setMessage(donationRequest.getMessage());
        
        // Update campaign collected amount
        campaign.setCollectedAmount(campaign.getCollectedAmount() + donationRequest.getAmount());
        campaignRepository.save(campaign);
        
        return donationRepository.save(donation);
    }
    
    public List<Donation> getDonationsByDonor(Long donorId) {
        Optional<Donor> donorOptional = donorRepository.findById(donorId);
        
        if (donorOptional.isEmpty()) {
            throw new RuntimeException("Donor not found");
        }
        
        Donor donor = donorOptional.get();
        
        return donationRepository.findByDonor(donor);
    }
    
    public List<Donation> getDonationsByNGO(Long ngoId) {
        Optional<NGO> ngoOptional = ngoRepository.findById(ngoId);
        
        if (ngoOptional.isEmpty()) {
            throw new RuntimeException("NGO not found");
        }
        
        NGO ngo = ngoOptional.get();
        
        return donationRepository.findByNgo(ngo);
    }
    
    public List<Donation> getDonationsByCampaign(Long campaignId) {
        Optional<Campaign> campaignOptional = campaignRepository.findById(campaignId);
        
        if (campaignOptional.isEmpty()) {
            throw new RuntimeException("Campaign not found");
        }
        
        Campaign campaign = campaignOptional.get();
        
        return donationRepository.findByCampaign(campaign);
    }
}

