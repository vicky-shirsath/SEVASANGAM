package com.sevasangam.service;

import com.sevasangam.dto.LoginRequest;
import com.sevasangam.dto.RegisterRequest;
import com.sevasangam.dto.UserDTO;
import com.sevasangam.model.User;
import com.sevasangam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserDTO registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        user.setPhone(registerRequest.getPhone());
        user.setAddress(registerRequest.getAddress());
        user.setCity(registerRequest.getCity());
        user.setState(registerRequest.getState());
        user.setCountry(registerRequest.getCountry());
        user.setPincode(registerRequest.getPincode());
        user.setVerified(false);
        user.setVerificationToken(UUID.randomUUID().toString());
        
        User savedUser = userRepository.save(user);
        
        return convertToDTO(savedUser);
    }
    
    public UserDTO login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOptional.get();
        
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        return convertToDTO(user);
    }
    
    public UserDTO getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        return convertToDTO(userOptional.get());
    }
    
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findById(id);
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        
        User user = userOptional.get();
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setCountry(userDTO.getCountry());
        user.setPincode(userDTO.getPincode());
        user.setProfileImage(userDTO.getProfileImage());
        
        User updatedUser = userRepository.save(user);
        
        return convertToDTO(updatedUser);
    }
    
    public boolean verifyUser(String token) {
        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(u -> token.equals(u.getVerificationToken()))
                .findFirst();
        
        if (userOptional.isEmpty()) {
            return false;
        }
        
        User user = userOptional.get();
        user.setVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);
        
        return true;
    }
    
    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setCity(user.getCity());
        userDTO.setState(user.getState());
        userDTO.setCountry(user.getCountry());
        userDTO.setPincode(user.getPincode());
        userDTO.setProfileImage(user.getProfileImage());
        userDTO.setVerified(user.isVerified());
        
        return userDTO;
    }
}

