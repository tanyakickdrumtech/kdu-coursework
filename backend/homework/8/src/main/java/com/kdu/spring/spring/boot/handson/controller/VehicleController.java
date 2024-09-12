package com.kdu.spring.spring.boot.handson.controller;
import com.kdu.spring.spring.boot.handson.entities.Vehicle;
import com.kdu.spring.spring.boot.handson.service.VehicleFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    private final VehicleFactoryService vehicleFactoryService;

    @Autowired
    public VehicleController(VehicleFactoryService vehicleFactoryService) {
        this.vehicleFactoryService = vehicleFactoryService;
    }


    @PostMapping("/createVehicle")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle){
        vehicleFactoryService.getInventoryStore().addVehicle(vehicle);
        return ResponseEntity.ok("Vehicle Successfully added in factory inventory");
    }

    @GetMapping("/getVehicle/{id}")
    public Vehicle getVehicle(@PathVariable long id){
        return vehicleFactoryService.getInventoryStore().getVehicleById(id);
    }

    @PutMapping("/updateVehicle/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable long id,@RequestBody Vehicle vehicle){
        if(vehicleFactoryService.getInventoryStore().updateVehicleById(id,vehicle)) {
            return ResponseEntity.ok("Vehicle Updated SuccessFully");
        }
        else{
            return ResponseEntity.ok("Vehicle id not found");
        }
    }
    @DeleteMapping("/deleteVehicle/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable long id) {
        if (vehicleFactoryService.getInventoryStore().deleteVehicleById(id)) {
            return ResponseEntity.ok("Vehicle Deleted Succesfully");
        } else {
            return ResponseEntity.ok("Vehicle with specified id not found");
        }
    }
    @GetMapping("/getMostExpensiveVehicle")
    public Vehicle getMostExpensiveVehicle(){
        return vehicleFactoryService.getInventoryStore().findMostExpensiveVehicle();
    }
    @GetMapping("/getLeastExpensiveVehicle")
    public Vehicle getLeastExpensiveVehicle(){
        return vehicleFactoryService.getInventoryStore().findLeastExpensiveVehicle();
    }
}