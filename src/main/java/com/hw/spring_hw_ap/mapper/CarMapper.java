package com.hw.spring_hw_ap.mapper;

import com.hw.spring_hw_ap.dto.CarDto;
import com.hw.spring_hw_ap.entity.Car;

public class CarMapper {

    public static CarDto toDto(Car car) {
        CarDto carDto = new CarDto();
        carDto.setId(car.getId());
        carDto.setModel(car.getModel());
        carDto.setEnginePower(car.getEnginePower());
        carDto.setTorque(car.getTorque());
        carDto.setLastMaintenanceTimestamp(car.getLastMaintenanceTimestamp());
        return carDto;
    }

    public static Car toEntity(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setModel(carDto.getModel());
        car.setEnginePower(carDto.getEnginePower());
        car.setTorque(carDto.getTorque());
        car.setLastMaintenanceTimestamp(carDto.getLastMaintenanceTimestamp());
        return car;
    }
}