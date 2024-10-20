package com.hw.spring_hw_ap.controller;

import com.hw.spring_hw_ap.models.Owner;
import com.hw.spring_hw_ap.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        Optional<Owner> owner = ownerService.getOwnerById(id);
        return owner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        return new ResponseEntity<>(ownerService.saveOwner(owner), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner updatedOwner) {
        Optional<Owner> existingOwner = ownerService.getOwnerById(id);
        if (existingOwner.isPresent()) {
            Owner owner = existingOwner.get();
            owner.setUsername(updatedOwner.getUsername());
            owner.setEmail(updatedOwner.getEmail());
            return new ResponseEntity<>(ownerService.saveOwner(owner), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long id) {
        boolean deleted = ownerService.deleteOwner(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Owner cannot be deleted because they own cars.");
        }
    }
}
