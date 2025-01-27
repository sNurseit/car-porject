package com.nurseit.carproject.controller;

import com.nurseit.carproject.dto.CarFilterDto;
import com.nurseit.carproject.entity.Car;
import com.nurseit.carproject.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("cars")
@Validated
public class CarController {
    private final CarService carService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<Car>> filter(
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) BigDecimal price,
            @RequestParam(required = false) String vin,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ) {
        Page<Car> filteredCars = carService.filter(
                CarFilterDto.builder()
                        .make(make)
                        .model(model)
                        .year(year)
                        .price(price)
                        .vin(vin)
                        .page(page)
                        .size(size)
                        .build());
        return ResponseEntity.ok(filteredCars);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated Car car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Validated Car car) {
        return ResponseEntity.ok(carService.update(id, car));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        carService.deleteById(id);
    }
}
