package kdubackend.spring.handon4.controller;
import jakarta.validation.Valid;
import kdubackend.spring.handon4.dto.VehicleDto;
import kdubackend.spring.handon4.entity.Vehicle;
import kdubackend.spring.handon4.exception.BadRequestException;
import kdubackend.spring.handon4.exception.ResourceNotFoundException;
import kdubackend.spring.handon4.service.VehicleFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@Scope("prototype")
public class VehicleController {

    private final VehicleFactoryService vehicleFactoryService;

    @Autowired
    public VehicleController(VehicleFactoryService vehicleFactoryService) {
        this.vehicleFactoryService = vehicleFactoryService;
    }


    @PostMapping("/vehicle")
    public ResponseEntity<String> addVehicle(@RequestBody @Valid VehicleDto vehicleDto) throws BadRequestException {
        Vehicle vehicle=Vehicle.convertVehicleDtoToVehicle(vehicleDto);
        vehicleFactoryService.getInventoryStore().addVehicle(vehicle);
        return ResponseEntity.ok("Vehicle Successfully added in factory inventory");
    }

    @GetMapping("/vehicle/{id}")
    public Vehicle getVehicle(@PathVariable long id) throws ResourceNotFoundException {
        return vehicleFactoryService.getInventoryStore().getVehicleById(id);
    }

    @PutMapping("/vehicle/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable long id, @RequestBody @Valid Vehicle vehicle) throws ResourceNotFoundException {
        return vehicleFactoryService.getInventoryStore().updateVehicleById(id, vehicle) ?
                ResponseEntity.ok("Vehicle Updated SuccessFully") :
                ResponseEntity.ok("Vehicle id not found");

    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable long id) throws ResourceNotFoundException {
        return vehicleFactoryService.getInventoryStore().deleteVehicleById(id) ?
                ResponseEntity.ok("Vehicle Deleted Successfully") :
                ResponseEntity.ok("Vehicle with specified id not found");
    }
}
