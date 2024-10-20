package com.hw.spring_hw_ap.service;

import com.hw.spring_hw_ap.dto.CarDto;
import com.hw.spring_hw_ap.mapper.CarMapper;
import com.hw.spring_hw_ap.models.Car;
import com.hw.spring_hw_ap.models.Owner;
import com.hw.spring_hw_ap.repository.CarRepository;
import com.hw.spring_hw_ap.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;
    private final JavaMailSender mailSender; // Добавлено для отправки почты

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
        Optional<Owner> ownerOpt = ownerRepository.findByUsername(carDto.getOwnerUsername());
        if (ownerOpt.isEmpty()) {
            throw new RuntimeException("Owner not found");
        }

        Car car = CarMapper.toEntity(carDto);
        car.setOwner(ownerOpt.get());
        return CarMapper.toDto(carRepository.save(car));
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    // Добавлено: Отправка письма владельцам авто с истекшим сроком обслуживания
    public void sendMaintenanceReminder() {
        List<Car> cars = carRepository.findAll();
        Map<Owner, List<Car>> carsByOwner = new HashMap<>();

        for (Car car : cars) {
            if (car.getLastMaintenanceTimestamp() != null && car.getLastMaintenanceTimestamp().isBefore(LocalDateTime.now().minusYears(1))) {
                carsByOwner.computeIfAbsent(car.getOwner(), owner -> new ArrayList<>()).add(car);
            }
        }

        for (Map.Entry<Owner, List<Car>> entry : carsByOwner.entrySet()) {
            Owner owner = entry.getKey();
            List<Car> overdueCars = entry.getValue();

            String emailBody = "The following cars require maintenance:\n";
            for (Car car : overdueCars) {
                emailBody += "- " + car.getModel() + " (Last maintenance: " + car.getLastMaintenanceTimestamp() + ")\n";
            }

            sendEmail(owner.getEmail(), "Maintenance Reminder", emailBody);
        }
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}