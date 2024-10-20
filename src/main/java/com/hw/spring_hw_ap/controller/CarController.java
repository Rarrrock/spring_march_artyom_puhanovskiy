package com.hw.spring_hw_ap.controller;

import com.hw.spring_hw_ap.dto.CarDto;
import com.hw.spring_hw_ap.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/search")
    public List<CarDto> searchCars(@RequestParam int minEnginePower, @RequestParam int maxEnginePower) {
        return carService.findByEnginePowerBetween(minEnginePower, maxEnginePower);
    }

    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @PostMapping
    public CarDto createCar(@RequestBody CarDto carDto) {
        return carService.save(carDto);
    }

    @PutMapping("/{id}")
    public CarDto updateCar(@PathVariable Long id, @RequestBody CarDto carDto) {
        carDto.setId(id);
        return carService.save(carDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
    }
}