package kdubackend.spring.handon4.entity;


import kdubackend.spring.handon4.dto.VehicleDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String name;
    private double price;
    private long id;

    public static Vehicle convertVehicleDtoToVehicle(VehicleDto vehicleDto){
        return new Vehicle(vehicleDto.getName(),vehicleDto.getPrice(),vehicleDto.getId());
    }
}
