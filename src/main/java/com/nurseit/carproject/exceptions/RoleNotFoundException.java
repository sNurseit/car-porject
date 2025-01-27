package com.nurseit.carproject.exceptions;

public class RoleNotFoundException extends NotFoundInDatabaseException {
    public RoleNotFoundException(String message) {
        super("Role not found: " + message);
    }
}
