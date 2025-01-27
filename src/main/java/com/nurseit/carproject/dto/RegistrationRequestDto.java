package com.nurseit.carproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequestDto {
    private String username;
    private String password;
}