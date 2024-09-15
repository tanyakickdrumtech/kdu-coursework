package kdu.spring.service;

import jakarta.annotation.PostConstruct;
import kdu.spring.entities.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class VehicleService {
    private List<Vehicle> vehicles=new ArrayList<>();

    @Autowired
    private TyreService tyreService;

    @Autowired
    private SpeakerService speakerService;

    /**
     * This post construct is Java annotation used
     * to mark a method that should be executed
     * after a bean is constructed and before it is put into service
     */
    @PostConstruct
    /**
     * this is a method to add vehicle object to list that is generating list
     */
    public void addVehicles(){
        boolean flag = false;
        for(int i = 0 ; i < Math.random()*500 ; i++){
            if(i%4 == 0){
                vehicles.add(flag ? new Vehicle(speakerService.speaker1(), tyreService.tyre2(), Math.random() * 100000) : new Vehicle(speakerService.speaker2(), tyreService.tyre2(), Math.random() * 100000));
                flag = !flag;
            }
            else {
                vehicles.add( flag ? new Vehicle(speakerService.speaker1(), tyreService.tyre1(), Math.random() * 100000) : new Vehicle(speakerService.speaker2(), tyreService.tyre1(), Math.random() * 100000));
            }
        }
    }

    /**
     * this method is used to find the most expensive vehicle using stream
     * @return
     */
    public Vehicle findMostExpensiveVehicle(){
        return vehicles.stream()
                .max(Comparator.comparing(Vehicle::getPrice))
                .orElse(null);
    }
}
