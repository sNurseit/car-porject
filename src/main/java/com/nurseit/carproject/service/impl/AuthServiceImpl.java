package com.nurseit.carproject.service.impl;

import com.nurseit.carproject.dto.LoginRequestDto;
import com.nurseit.carproject.dto.LoginResponseDto;
import com.nurseit.carproject.dto.RegistrationRequestDto;
import com.nurseit.carproject.entity.Role;
import com.nurseit.carproject.entity.User;
import com.nurseit.carproject.repository.UserRepository;
import com.nurseit.carproject.service.AuthService;
import com.nurseit.carproject.service.RoleService;
import com.nurseit.carproject.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;

    @Override
    public void registration(RegistrationRequestDto registrationRequest) {
        if (repository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User with the same username already exists");
        }
        create(registrationRequest.getUsername(), registrationRequest.getPassword());
    }


    private void create(String username, String password) {
        Set<Role> role = new HashSet<>();
        role.add(roleService.findByName("ROLE_USER"));
        User user = User.builder()
                .roles(role)
                .isEnabled(true)
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        repository.save(
                user
        );
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<User> userOptional = repository.findByUsername(loginRequestDto.getUsername());
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return new LoginResponseDto(jwtUtil.generateToken(user.getId(), user.getRoles()));
    }
}
