package com.kdu.spring.spring.boot.handson.repository;

import com.kdu.spring.spring.boot.handson.entities.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Scope("prototype")
public class InventoryStore {
    private List<Vehicle> vehicles=new ArrayList<>();


    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }
    public List<Vehicle> getVehicles() {
        return vehicles;
    }


    /**
     * method to get vehicle by the id
     * @param id
     * @return
     */
    public Vehicle getVehicleById(long id){
        return vehicles.stream()
                .filter(vehicle -> vehicle.getId()==id)
                .findFirst()
                .orElse(null);
    }

    /**
     * method to update the vehicle
     * @param id
     * @param vehicle
     * @return
     */
    public boolean updateVehicleById(long id,Vehicle vehicle){
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i).getId()==id) {
                vehicles.set(i,vehicle);
                return true;
            }
        }
        return false;
    }

    /**
     * method to delete the vehicle
     * @param id
     * @return
     */

    public boolean deleteVehicleById(Long id) {
        Iterator<Vehicle> iterator = vehicles.iterator();
        while (iterator.hasNext()) {
            Vehicle vehicle = iterator.next();
            if (vehicle.getId()==id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * method to find the most expensive vehicle
     * @return
     */

    public Vehicle findMostExpensiveVehicle(){
        return vehicles.stream()
                .max(Comparator.comparing(Vehicle::getPrice))
                .orElse(null);
    }

    /**
     * method to find the least expensive vehicle
     * @return
     */
    public Vehicle findLeastExpensiveVehicle(){
        return vehicles.stream()
                .min(Comparator.comparing(Vehicle::getPrice))
                .orElse(null);
    }
}
