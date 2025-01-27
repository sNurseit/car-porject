package com.nurseit.carproject.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super("Role not found: " + message);
    }
}
