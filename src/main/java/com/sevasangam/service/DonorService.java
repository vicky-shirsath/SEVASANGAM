package com.sevasangam.service;

import com.sevasangam.model.Donor;
import com.sevasangam.model.User;
import com.sevasangam.repository.DonorRepository;
import com.sevasangam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Donor registerDonor(Long userId, String occupation, String panCard) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOptional.get();
        
        if (!user.getRole().equals("DONOR")) {
            throw new RuntimeException("User is not a donor");
        }
        
        Donor donor = new Donor();
        donor.setUser(user);
        donor.setOccupation(occupation);
        donor.setPanCard(panCard);
        
        return donorRepository.save(donor);
    }
    
    public Donor getDonorById(Long id) {
        Optional<Donor> donorOptional = donorRepository.findById(id);
        
        if (donorOptional.isEmpty()) {
            throw new RuntimeException("Donor not found");
        }
        
        return donorOptional.get();
    }
    
    public Donor getDonorByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOptional.get();
        
        Optional<Donor> donorOptional = donorRepository.findByUser(user);
        
        if (donorOptional.isEmpty()) {
            throw new RuntimeException("Donor not found for this user");
        }
        
        return donorOptional.get();
    }
    
    public Donor updateDonor(Long id, Donor donorDetails) {
        Optional<Donor> donorOptional = donorRepository.findById(id);
        
        if (donorOptional.isEmpty()) {
            throw new RuntimeException("Donor not found");
        }
        
        Donor donor = donorOptional.get();
        donor.setOccupation(donorDetails.getOccupation());
        donor.setPanCard(donorDetails.getPanCard());
        
        return donorRepository.save(donor);
    }
}

