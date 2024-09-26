package kdu.spring.service;

import jakarta.annotation.PostConstruct;
import kdu.spring.entities.Vehicle;
import kdu.spring.repository.InventoryStore;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;


public abstract class VehicleService {

    private TyreService tyreService;

    private SpeakerService speakerService;

    private InventoryStore inventoryStore;

    @Autowired
    protected VehicleService(TyreService tyreService,SpeakerService speakerService,InventoryStore inventoryStore){
        this.tyreService=tyreService;
        this.speakerService=speakerService;
        this.inventoryStore=inventoryStore;
    }

    /**
     * this method is basically generating and add vehicle to the list of type vehicles and this is
     *  made postcontruct that is it will be called/executed when the bean is created
     */

    @PostConstruct
    public void addVehicles(){
        boolean flag = false;
        for(int i = 0 ; i < Math.random()*500 ; i++){
            if(i%4 == 0){
                inventoryStore.getVehicles().add(flag ? new Vehicle(speakerService.speaker1(), tyreService.tyre2(), generatePrice()) : new Vehicle(speakerService.speaker2(), tyreService.tyre2(), generatePrice()));
                flag = !flag;
            }
            else {
                inventoryStore.getVehicles().add( flag ? new Vehicle(speakerService.speaker1(), tyreService.tyre1(),generatePrice()) : new Vehicle(speakerService.speaker2(), tyreService.tyre1(),generatePrice()));
            }
        }

    }

    /**
     * method to find the most expensive vehicle
     * @return
     */

    public Vehicle findMostExpensiveVehicle(){
        return inventoryStore.getVehicles().stream()
                .max(Comparator.comparing(Vehicle::getPrice))
                .orElse(null);
    }

    /**
     * method to find the least expensive vehicle
     * @return
     */
    public Vehicle findLeastExpensiveVehicle(){
        return inventoryStore.getVehicles().stream()
                .min(Comparator.comparing(Vehicle::getPrice))
                .orElse(null);
    }

    /**
     * abstract method
     * @return
     */
    public abstract double generatePrice();
}
