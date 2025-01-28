package com.nurseit.carproject.controller;


import com.nurseit.carproject.dto.ErrorMessage;
import com.nurseit.carproject.dto.LoginRequestDto;
import com.nurseit.carproject.dto.LoginResponseDto;
import com.nurseit.carproject.dto.RegistrationRequestDto;
import com.nurseit.carproject.entity.Car;
import com.nurseit.carproject.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
@Tag(name = "Auth Controller", description = "Operations related to auth")
public class AuthController {

    private final AuthService service;


    @Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully login",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Username or password is incorrect",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegistrationRequestDto registrationRequest) {
        service.registration(registrationRequest);
        return new ResponseEntity<>(HttpEntity.EMPTY, HttpStatus.OK);
    }
}