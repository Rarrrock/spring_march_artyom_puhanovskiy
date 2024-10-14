package com.hw.spring_hw_ap.mapper_3;

import com.hw.spring_hw_ap.dto_3.CarDto;
import com.hw.spring_hw_ap.models_2.Car;

public class CarMapper {

    public static CarDto toDto(Car car23) {
        CarDto carDto = new CarDto();
        carDto.setId(car23.getId());
        carDto.setModel(car23.getModel());
        carDto.setEnginePower(car23.getEnginePower());
        carDto.setTorque(car23.getTorque());
        return carDto;
    }

    public static Car toEntity(CarDto carDto) {
        Car car23 = new Car();
        car23.setId(carDto.getId());
        car23.setModel(carDto.getModel());
        car23.setEnginePower(carDto.getEnginePower());
        car23.setTorque(carDto.getTorque());
        return car23;
    }
}
