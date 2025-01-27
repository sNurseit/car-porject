package com.nurseit.carproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarFilterDto {
    String make;
    String model;
    Integer year;
    BigDecimal price;
    String vin;
    Integer size;
    Integer page;
}
