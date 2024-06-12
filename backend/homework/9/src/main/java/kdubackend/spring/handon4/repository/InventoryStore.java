package kdubackend.spring.handon4.repository;
import jakarta.validation.Valid;
import kdubackend.spring.handon4.entity.Vehicle;
import kdubackend.spring.handon4.exception.BadRequestException;
import kdubackend.spring.handon4.exception.ResourceNotFoundException;
import kdubackend.spring.handon4.logging.LoggerDisplay;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
@Scope("prototype")
public class InventoryStore {
    private List<Vehicle> vehicles = new ArrayList<>();


    public void addVehicle(@Valid Vehicle vehicle) throws BadRequestException {
        try {
            int size = vehicles.size();
            vehicles.add(vehicle);
            if (size == vehicles.size()) {
                throw new RuntimeException();
            }
            LoggerDisplay.logInfo("Vehicle added Succesfully");

        } catch (RuntimeException e) {
            LoggerDisplay.logError("Exception while adding vehicle");
            throw new BadRequestException("Vehicle of invalid type");
        }
    }

    /**
     * method to get vehicle by the id
     *
     * @param id
     * @return
     */
    public Vehicle getVehicleById(long id) throws ResourceNotFoundException {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getId() == id)
                .findFirst()
                .orElseThrow(() -> {
                    LoggerDisplay.logError("Exception while retrieving vehicle by id");
                    return new ResourceNotFoundException("Vehice ID not found");
                });
    }

    /**
     * method to update the vehicle
     *
     * @param id
     * @param vehicle
     * @return
     */
    public boolean updateVehicleById(long id, @Valid Vehicle vehicle) throws ResourceNotFoundException {
        try {
            for (int i = 0; i < vehicles.size(); i++) {
                if (vehicles.get(i).getId() == id) {
                    vehicles.set(i, vehicle);
                    LoggerDisplay.logInfo("Vehicle updated successfully");
                    return true;
                }
            }
            throw new NoSuchElementException();
        } catch (NoSuchElementException e) {
            LoggerDisplay.logError("Exception while updating vehicle-ResourceNotFound");
            throw new ResourceNotFoundException("The vehicle with the required id" + id + " not found");
        }

    }

    /**
     * method to delete the vehicle
     *
     * @param id
     * @return
     */

    public boolean deleteVehicleById(Long id) throws ResourceNotFoundException {
        try {
            Iterator<Vehicle> iterator = vehicles.iterator();
            while (iterator.hasNext()) {
                Vehicle vehicle = iterator.next();
                if (vehicle.getId() == id) {
                    iterator.remove();
                    LoggerDisplay.logInfo("Vehicle deleted successfully");
                    return true;
                }
            }
            throw new NoSuchElementException();
        }
         catch (NoSuchElementException e) {
            LoggerDisplay.logError("Exception occured while deleting vehicle");
             throw new ResourceNotFoundException("The vehicle with the required id" + id + " not found");
        }

    }

}

