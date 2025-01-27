package com.nurseit.carproject.service.impl;

import com.nurseit.carproject.dto.CarFilterDto;
import com.nurseit.carproject.entity.Car;
import com.nurseit.carproject.exceptions.CarNotFoundException;
import com.nurseit.carproject.repository.CarRepository;
import com.nurseit.carproject.service.CarService;
import com.nurseit.carproject.specification.CarSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(UUID id) {
        return carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public Car update(UUID id, Car carToSave) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));

        car.setYear(carToSave.getYear());
        car.setVin(carToSave.getVin());
        car.setMake(carToSave.getMake());
        car.setModel(carToSave.getModel());
        car.setPrice(carToSave.getPrice());
        return carRepository.save(car);
    }

    @Override
    public void deleteById(UUID id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Page<Car> filter(CarFilterDto filter) {
        var specification = new CarSpecification(filter);
        int page = Optional.ofNullable(filter.getPage()).orElse(0);
        int size = Optional.ofNullable(filter.getSize()).orElse(10);

        return carRepository.findAll(
                specification,
                PageRequest.of(page, size));
    }

}
