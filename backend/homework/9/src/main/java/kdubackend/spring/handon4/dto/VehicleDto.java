package kdubackend.spring.handon4.dto;

import kdubackend.spring.handon4.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDto {
    private String name;
    private double price;
    private long id;

    public static VehicleDto convertVehicleToVehicleDto(Vehicle vehicle){
        return new VehicleDto(vehicle.getName(),vehicle.getPrice(),vehicle.getId());
    }
}
