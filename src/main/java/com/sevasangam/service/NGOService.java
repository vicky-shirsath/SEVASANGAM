package com.sevasangam.service;

import com.sevasangam.dto.NGORegistrationRequest;
import com.sevasangam.model.NGO;
import com.sevasangam.model.User;
import com.sevasangam.repository.NGORepository;
import com.sevasangam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class NGOService {

    @Autowired
    private NGORepository ngoRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public NGO registerNGO(Long userId, NGORegistrationRequest request) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOptional.get();
        
        if (!user.getRole().equals("NGO")) {
            throw new RuntimeException("User is not an NGO");
        }
        
        NGO ngo = new NGO();
        ngo.setUser(user);
        ngo.setNgoName(request.getNgoName());
        ngo.setNgoDescription(request.getNgoDescription());
        ngo.setNgoRegistrationNumber(request.getNgoRegistrationNumber());
        ngo.setNgoType(request.getNgoType());
        ngo.setNgoWebsite(request.getNgoWebsite());
        ngo.setVerified(false);
        
        return ngoRepository.save(ngo);
    }
    
    public NGO getNGOById(Long id) {
        Optional<NGO> ngoOptional = ngoRepository.findById(id);
        
        if (ngoOptional.isEmpty()) {
            throw new RuntimeException("NGO not found");
        }
        
        return ngoOptional.get();
    }
    
    public NGO getNGOByUserId(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOptional.get();
        
        Optional<NGO> ngoOptional = ngoRepository.findByUser(user);
        
        if (ngoOptional.isEmpty()) {
            throw new RuntimeException("NGO not found for this user");
        }
        
        return ngoOptional.get();
    }
    
    public List<NGO> getAllNGOs() {
        return ngoRepository.findAll();
    }
    
    public List<NGO> getVerifiedNGOs() {
        return ngoRepository.findByIsVerified(true);
    }
    
    public List<NGO> getNGOsByType(String type) {
        return ngoRepository.findByNgoType(type);
    }
    
    public NGO verifyNGO(Long id) {
        Optional<NGO> ngoOptional = ngoRepository.findById(id);
        
        if (ngoOptional.isEmpty()) {
            throw new RuntimeException("NGO not found");
        }
        
        NGO ngo = ngoOptional.get();
        ngo.setVerified(true);
        
        return ngoRepository.save(ngo);
    }
    
    public NGO updateNGO(Long id, NGO ngoDetails) {
        Optional<NGO> ngoOptional = ngoRepository.findById(id);
        
        if (ngoOptional.isEmpty()) {
            throw new RuntimeException("NGO not found");
        }
        
        NGO ngo = ngoOptional.get();
        ngo.setNgoName(ngoDetails.getNgoName());
        ngo.setNgoDescription(ngoDetails.getNgoDescription());
        ngo.setNgoType(ngoDetails.getNgoType());
        ngo.setNgoWebsite(ngoDetails.getNgoWebsite());
        ngo.setNgoLogo(ngoDetails.getNgoLogo());
        
        return ngoRepository.save(ngo);
    }
}

