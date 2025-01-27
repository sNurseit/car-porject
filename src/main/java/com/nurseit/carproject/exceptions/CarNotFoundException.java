package com.nurseit.carproject.exceptions;

import java.util.UUID;

public class CarNotFoundException extends NotFoundInDatabaseException {
  public CarNotFoundException(UUID id) {
    super("Car with ID " + id + " not found in the database.");
  }
}
