package com.hw.spring_hw_ap.service_2;

import com.hw.spring_hw_ap.dto_3.CarDto;
import com.hw.spring_hw_ap.mapper_3.CarMapper;
import com.hw.spring_hw_ap.models_2.Car;
import com.hw.spring_hw_ap.repository_2.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public List<CarDto> findByEnginePowerBetween(int minEnginePower, int maxEnginePower) {
        return carRepository.findByEnginePowerBetween(minEnginePower, maxEnginePower)
                .stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<CarDto> findAll() {
        return carRepository.findAll()
                .stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }

    public CarDto findById(Long id) {
        return carRepository.findById(id)
                .map(CarMapper::toDto)
                .orElseThrow();
    }

    public CarDto save(CarDto carDto) {
        Car car = CarMapper.toEntity(carDto);
        return CarMapper.toDto(carRepository.save(car));
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}