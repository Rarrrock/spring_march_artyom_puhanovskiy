package com.hw.spring_hw_ap.controller;

import com.hw.spring_hw_ap.dto.CarDto;
import com.hw.spring_hw_ap.service.CarService;
import com.hw.spring_hw_ap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;
    private final UserService ownerService;

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public List<CarDto> searchCars(@RequestParam int minEnginePower, @RequestParam int maxEnginePower) {
        return carService.findByEnginePowerBetween(minEnginePower, maxEnginePower);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<CarDto> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carService.save(carDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public CarDto updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();

        CarDto existingCar = carService.findById(id);
        if (!existingCar.getOwnerUsername().equals(currentUsername) && !authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
            throw new RuntimeException("Access denied");
        }

        carDto.setId(id);
        return carService.save(carDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteCar(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = ((UserDetails) authentication.getPrincipal()).getUsername();

        CarDto existingCar = carService.findById(id);
        if (!existingCar.getOwnerUsername().equals(currentUsername) && !authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ADMIN"))) {
            throw new RuntimeException("Access denied");
        }

        carService.deleteById(id);
    }
}