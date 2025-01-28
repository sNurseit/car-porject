package com.nurseit.carproject.dto;

import com.nurseit.carproject.validator.CurrentYearMax;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {

    @NotNull(message = "Make cannot be null")
    @Size(max = 255, message = "Make cannot be longer than 255 characters")
    private String make;

    @NotNull(message = "Model cannot be null")
    @Size(max = 255, message = "Model cannot be longer than 255 characters")
    private String model;

    @Min(value = 1886, message = "Year must be after 1886")
    @CurrentYearMax
    private int year;

    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 13, fraction = 2, message = "Price must be a valid number with up to 13 digits before the decimal and 2 digits after the decimal")
    private BigDecimal price;

    @NotNull(message = "VIN cannot be null")
    @Size(min = 17, max = 17, message = "VIN must be exactly 17 characters")
    private String vin;

}
