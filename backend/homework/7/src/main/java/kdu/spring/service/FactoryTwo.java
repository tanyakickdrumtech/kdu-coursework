package kdu.spring.service;
import kdu.spring.repository.InventoryStore;
import org.springframework.stereotype.Component;


@Component("factoryTwo")
public class FactoryTwo extends VehicleService {

    /**
     * constructor
     * @param tyreService
     * @param speakerService
     * @param inventoryStore
     */
    public FactoryTwo(TyreService tyreService, SpeakerService speakerService, InventoryStore inventoryStore) {
        super(tyreService,speakerService,inventoryStore);
    }

    /**
     * to generate the random price for the vehicle
     * @return
     */
    public double generatePrice(){
        return Math.random() * 100000;
    }
}
