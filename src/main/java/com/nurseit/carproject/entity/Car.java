package com.nurseit.carproject.entity;

import com.nurseit.carproject.validator.CurrentYearMax;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Make cannot be null")
    @Size(max = 255, message = "Make cannot be longer than 255 characters")
    @Column(nullable = false)
    private String make;

    @NotNull(message = "Model cannot be null")
    @Size(max = 255, message = "Model cannot be longer than 255 characters")
    @Column(nullable = false)
    private String model;

    @Min(value = 1886, message = "Year must be after 1886")
    @CurrentYearMax
    @Column(nullable = false)
    private int year;

    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 13, fraction = 2, message = "Price must be a valid number with up to 13 digits before the decimal and 2 digits after the decimal")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "VIN cannot be null")
    @Size(min = 17, max = 17, message = "VIN must be exactly 17 characters")
    @Column(nullable = false, unique = true)
    private String vin;

}
