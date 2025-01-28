package com.nurseit.carproject.service;

import com.nurseit.carproject.dto.LoginRequestDto;
import com.nurseit.carproject.dto.LoginResponseDto;
import com.nurseit.carproject.dto.RegistrationRequestDto;

public interface AuthService {
    void registration(RegistrationRequestDto registrationRequest);
    LoginResponseDto login(LoginRequestDto loginRequest);
}
