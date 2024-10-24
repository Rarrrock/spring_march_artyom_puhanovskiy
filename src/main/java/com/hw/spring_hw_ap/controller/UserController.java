package com.hw.spring_hw_ap.controller;

import com.hw.spring_hw_ap.entity.User;
import com.hw.spring_hw_ap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService ownerService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOwnerById(@PathVariable Long id) {
        Optional<User> owner = ownerService.getOwnerById(id);
        return owner.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> createOwner(@RequestBody User owner) {
        return new ResponseEntity<>(ownerService.saveOwner(owner), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateOwner(@PathVariable Long id, @RequestBody User updatedOwner) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();

        Optional<User> existingOwner = ownerService.getOwnerById(id);
        if (existingOwner.isPresent()) {
            User owner = existingOwner.get();

            if (!currentUsername.equals(owner.getUsername()) && !authentication.getAuthorities().stream()
                    .anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            owner.setUsername(updatedOwner.getUsername());
            owner.setEmail(updatedOwner.getEmail());
            return new ResponseEntity<>(ownerService.saveOwner(owner), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();

        Optional<User> existingOwner = ownerService.getOwnerById(id);
        if (existingOwner.isPresent()) {
            User owner = existingOwner.get();

            if (!currentUsername.equals(owner.getUsername()) && !authentication.getAuthorities().stream()
                    .anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            boolean deleted = ownerService.deleteOwner(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Owner cannot be deleted because they own cars.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
