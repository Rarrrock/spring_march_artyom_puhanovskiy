package com.hw.spring_hw_ap.service;

import com.hw.spring_hw_ap.models.Car;
import com.hw.spring_hw_ap.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<Car> findByEnginePowerBetween(int minEnginePower, int maxEnginePower) {
        return carRepository.findByEnginePowerBetween(minEnginePower, maxEnginePower);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow();
    }

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
