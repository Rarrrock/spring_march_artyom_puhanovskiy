package com.hw.spring_hw_ap.service;

import com.hw.spring_hw_ap.models.Owner;
import com.hw.spring_hw_ap.repository.CarRepository;
import com.hw.spring_hw_ap.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private CarRepository carRepository;

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public boolean deleteOwner(Long id) {
        if (carRepository.existsByOwnerId(id)) {
            return false;
        }
        ownerRepository.deleteById(id);
        return true;
    }

    public Optional<Owner> getOwnerByUsername(String username) {
        return ownerRepository.findByUsername(username);
    }
}