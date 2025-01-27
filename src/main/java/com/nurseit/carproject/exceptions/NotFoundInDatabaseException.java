package com.nurseit.carproject.exceptions;

public class NotFoundInDatabaseException extends RuntimeException {
    public NotFoundInDatabaseException(String message) {
        super(message);
    }
}
