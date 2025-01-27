package com.nurseit.carproject.controller;


import com.nurseit.carproject.dto.LoginRequestDto;
import com.nurseit.carproject.dto.RegistrationRequestDto;
import com.nurseit.carproject.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequestDto registrationRequest) {
        service.registration(registrationRequest);
        return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
    }
}