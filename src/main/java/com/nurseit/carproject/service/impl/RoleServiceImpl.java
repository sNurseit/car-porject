package com.nurseit.carproject.service.impl;

import com.nurseit.carproject.entity.Role;
import com.nurseit.carproject.exceptions.RoleNotFoundException;
import com.nurseit.carproject.repository.RoleRepository;
import com.nurseit.carproject.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public Role findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
    }
}
