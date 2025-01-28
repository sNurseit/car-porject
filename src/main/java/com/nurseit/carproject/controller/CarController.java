package com.nurseit.carproject.controller;

import com.nurseit.carproject.dto.CarDto;
import com.nurseit.carproject.dto.CarFilterDto;
import com.nurseit.carproject.dto.ErrorMessage;
import com.nurseit.carproject.entity.Car;
import com.nurseit.carproject.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("cars")
@Validated
@Tag(name = "Car Controller", description = "Operations related to car in the database")
public class CarController {
    private final CarService carService;

    @Operation(summary = "Returns all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car[].class))),
            @ApiResponse(responseCode = "500", description = "Car retrieved failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @Operation(summary = "Returns filtered cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "500", description = "Car retrieved failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
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

    @Operation(summary = "Returns car by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "500", description = "Car retrieved failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "400", description = "Car was not found in the database",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @Operation(summary = "Saves car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car saved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "500", description = "Car saved failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Validated CarDto car) {
        return ResponseEntity.ok(carService.save(car));
    }

    @Operation(summary = "Update car")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "500", description = "Car updated failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "400", description = "Car was not found in the database",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Validated CarDto car) {
        return ResponseEntity.ok(carService.update(id, car));
    }

    @Operation(summary = "Delete car by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car deleted successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Car.class))),
            @ApiResponse(responseCode = "500", description = "Car deleted failure",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            @ApiResponse(responseCode = "400", description = "Car was not found in the database",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        carService.deleteById(id);
    }
}
