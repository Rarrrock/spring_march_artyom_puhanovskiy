package com.hw.spring_hw_ap.mapper;

import com.hw.spring_hw_ap.dto.CarDto;
import com.hw.spring_hw_ap.entity.Car;
import com.hw.spring_hw_ap.entity.User;
import com.hw.spring_hw_ap.repository.UserRepository;

import java.util.Optional;

public class CarMapper {

    // Передаем UserRepository как аргумент
    public static Car toEntity(CarDto carDto, UserRepository userRepository) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setModel(carDto.getModel());
        car.setEnginePower(carDto.getEnginePower());
        car.setTorque(carDto.getTorque());
        car.setLastMaintenanceTimestamp(carDto.getLastMaintenanceTimestamp());

        // Устанавливаем пользователя по ownerUsername
        if (carDto.getOwnerUsername() != null) {
            Optional<User> ownerOpt = userRepository.findByUsername(carDto.getOwnerUsername());
            ownerOpt.ifPresent(car::setOwner);
        }

        return car;
    }

    public static CarDto toDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        carDto.setEnginePower(car.getEnginePower());
        carDto.setTorque(car.getTorque());
        carDto.setLastMaintenanceTimestamp(car.getLastMaintenanceTimestamp());
        carDto.setOwnerUsername(car.getOwner().getUsername());
        return carDto;
    }
}