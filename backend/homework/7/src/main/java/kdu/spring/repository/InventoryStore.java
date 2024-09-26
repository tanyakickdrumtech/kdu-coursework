package kdu.spring.repository;

import kdu.spring.entities.Vehicle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class InventoryStore {
    private List<Vehicle> vehicles=new ArrayList<>();


    public void addVehicle(Vehicle vehicle){
        vehicles.add(vehicle);
    }
    public List<Vehicle> getVehicles() {
        return vehicles;
    }
}
