package com.nurseit.carproject.mapper;

import com.nurseit.carproject.dto.CarDto;
import com.nurseit.carproject.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public Car toEntity(CarDto carDto) {
        if (carDto == null) {
            return null;
        }

        return Car.builder()
                .year(carDto.getYear())
                .vin(carDto.getVin())
                .make(carDto.getMake())
                .model(carDto.getModel())
                .price(carDto.getPrice())
                .build();
    }

    public CarDto toDto(Car car) {
        if (car == null) {
            return null;
        }

        return CarDto.builder()
                .year(car.getYear())
                .vin(car.getVin())
                .make(car.getMake())
                .model(car.getModel())
                .price(car.getPrice())
                .build();
    }
}
