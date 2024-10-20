package com.hw.spring_hw_ap.repository;

import com.hw.spring_hw_ap.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUsername(String username);
}
