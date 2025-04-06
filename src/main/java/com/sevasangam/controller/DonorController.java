package com.sevasangam.controller;

import com.sevasangam.model.Donor;
import com.sevasangam.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donors")
public class DonorController {

    @Autowired
    private DonorService donorService;
    
    @PostMapping("/register/{userId}")
    public ResponseEntity<Donor> registerDonor(
            @PathVariable Long userId,
            @RequestParam String occupation,
            @RequestParam String panCard) {
        Donor donor = donorService.registerDonor(userId, occupation, panCard);
        return ResponseEntity.ok(donor);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id) {
        Donor donor = donorService.getDonorById(id);
        return ResponseEntity.ok(donor);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Donor> getDonorByUserId(@PathVariable Long userId) {
        Donor donor = donorService.getDonorByUserId(userId);
        return ResponseEntity.ok(donor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor donorDetails) {
        Donor donor = donorService.updateDonor(id, donorDetails);
        return ResponseEntity.ok(donor);
    }
}

