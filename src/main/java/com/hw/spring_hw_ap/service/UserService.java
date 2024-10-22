package com.hw.spring_hw_ap.service;

import com.hw.spring_hw_ap.entity.User;
import com.hw.spring_hw_ap.repository.CarRepository;
import com.hw.spring_hw_ap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository ownerRepository;

    @Autowired
    private CarRepository carRepository;

    public List<User> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<User> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public User saveOwner(User owner) {
        if (ownerRepository.findByUsername(owner.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (ownerRepository.findByEmail(owner.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        return ownerRepository.save(owner);
    }

    public boolean deleteOwner(Long id) {
        if (carRepository.existsByOwnerId(id)) {
            return false;
        }
        ownerRepository.deleteById(id);
        return true;
    }

    public Optional<User> getOwnerByUsername(String username) {
        return ownerRepository.findByUsername(username);
    }
}