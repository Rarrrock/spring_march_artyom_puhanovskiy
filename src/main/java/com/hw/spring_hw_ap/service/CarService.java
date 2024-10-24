package com.hw.spring_hw_ap.service;

import com.hw.spring_hw_ap.dto.CarDto;
import com.hw.spring_hw_ap.mapper.CarMapper;
import com.hw.spring_hw_ap.entity.Car;
import com.hw.spring_hw_ap.entity.User;
import com.hw.spring_hw_ap.repository.CarRepository;
import com.hw.spring_hw_ap.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;

    // Поиск машин по диапазону мощности двигателя
    public List<CarDto> findByEnginePowerBetween(int minEnginePower, int maxEnginePower) {
        return carRepository.findByEnginePowerBetween(minEnginePower, maxEnginePower)
                .stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }

    // Поиск всех машин
    public List<CarDto> findAll() {
        return carRepository.findAll()
                .stream()
                .map(CarMapper::toDto)
                .collect(Collectors.toList());
    }

    // Поиск машины по ID
    public CarDto findById(Long id) {
        return carRepository.findById(id)
                .map(CarMapper::toDto)
                .orElseThrow();
    }

    // Сохранение машины
    public CarDto save(CarDto carDto) {
        // Ищем владельца по имени пользователя
        Optional<User> ownerOpt = userRepository.findByUsername(carDto.getOwnerUsername());
        if (ownerOpt.isEmpty()) {
            throw new RuntimeException("Owner not found");
        }

        // Преобразуем DTO в сущность, передаем userRepository для корректного сопоставления владельца
        Car car = CarMapper.toEntity(carDto, userRepository);
        car.setOwner(ownerOpt.get());
        return CarMapper.toDto(carRepository.save(car));
    }

    // Удаление машины по ID
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    // Отправка уведомлений владельцам, чьи машины нуждаются в обслуживании
    public void sendMaintenanceReminder() {
        List<Car> cars = carRepository.findAll();
        Map<User, List<Car>> carsByOwner = new HashMap<>();

        for (Car car : cars) {
            if (car.getLastMaintenanceTimestamp() != null &&
                    car.getLastMaintenanceTimestamp().isBefore(LocalDateTime.now().minusYears(1))) {
                carsByOwner.computeIfAbsent(car.getOwner(), owner -> new ArrayList<>()).add(car);
            }
        }

        for (Map.Entry<User, List<Car>> entry : carsByOwner.entrySet()) {
            User user = entry.getKey();
            List<Car> overdueCars = entry.getValue();

            String emailBody = "The following cars require maintenance:\n";
            for (Car car : overdueCars) {
                emailBody += "- " + car.getModel() + " (Last maintenance: " + car.getLastMaintenanceTimestamp() + ")\n";
            }

            sendEmail(user.getEmail(), "Maintenance Reminder", emailBody);
        }
    }

    // Метод для отправки email
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
}