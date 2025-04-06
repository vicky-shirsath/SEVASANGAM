package com.sevasangam.controller;

import com.sevasangam.dto.NGORegistrationRequest;
import com.sevasangam.model.NGO;
import com.sevasangam.service.NGOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ngos")
public class NGOController {

    @Autowired
    private NGOService ngoService;
    
    @PostMapping("/register/{userId}")
    public ResponseEntity<NGO> registerNGO(@PathVariable Long userId, @RequestBody NGORegistrationRequest request) {
        NGO ngo = ngoService.registerNGO(userId, request);
        return ResponseEntity.ok(ngo);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<NGO> getNGOById(@PathVariable Long id) {
        NGO ngo = ngoService.getNGOById(id);
        return ResponseEntity.ok(ngo);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<NGO> getNGOByUserId(@PathVariable Long userId) {
        NGO ngo = ngoService.getNGOByUserId(userId);
        return ResponseEntity.ok(ngo);
    }
    
    @GetMapping
    public ResponseEntity<List<NGO>> getAllNGOs() {
        List<NGO> ngos = ngoService.getAllNGOs();
        return ResponseEntity.ok(ngos);
    }
    
    @GetMapping("/verified")
    public ResponseEntity<List<NGO>> getVerifiedNGOs() {
        List<NGO> ngos = ngoService.getVerifiedNGOs();
        return ResponseEntity.ok(ngos);
    }
    
    @GetMapping("/type/{type}")
    public ResponseEntity<List<NGO>> getNGOsByType(@PathVariable String type) {
        List<NGO> ngos = ngoService.getNGOsByType(type);
        return ResponseEntity.ok(ngos);
    }
    
    @PutMapping("/verify/{id}")
    public ResponseEntity<NGO> verifyNGO(@PathVariable Long id) {
        NGO ngo = ngoService.verifyNGO(id);
        return ResponseEntity.ok(ngo);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<NGO> updateNGO(@PathVariable Long id, @RequestBody NGO ngoDetails) {
        NGO ngo = ngoService.updateNGO(id, ngoDetails);
        return ResponseEntity.ok(ngo);
    }
}

