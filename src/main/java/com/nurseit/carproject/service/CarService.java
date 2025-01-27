package com.nurseit.carproject.service;

import com.nurseit.carproject.entity.Car;
import com.nurseit.carproject.exceptions.CarNotFoundException;

import java.util.List;
import java.util.UUID;

/**
 * Interface for car-related operations.
 * Provides methods for CRUD (Create, Read, Update, Delete) operations on car entities.
 */
public interface CarService {

    /**
     * Retrieves a list of all cars.
     *
     * @return a {@link List} of {@link Car} objects.
     */
    List<Car> findAll();

    /**
     * Retrieves a car by its unique identifier.
     *
     * @param id the unique identifier of the car.
     * @return the {@link Car} object if found.
     * @throws CarNotFoundException if no car is found with the given ID.
     */
    Car findById(UUID id);

    /**
     * Updates the details of an existing car.
     *
     * @param id the unique identifier of the car to be updated.
     * @param car the {@link Car} object containing updated details.
     * @return the updated {@link Car} object.
     * @throws CarNotFoundException if no car is found with the given ID.
     */
    Car update(UUID id, Car car);

    /**
     * Deletes a car by its unique identifier.
     *
     * @param id the unique identifier of the car to be deleted.
     */
    void deleteById(UUID id);

    /**
     * Saves a new car to the database.
     *
     * @param car the {@link Car} object to be saved.
     * @return the saved {@link Car} object.
     */
    Car save(Car car);
}
