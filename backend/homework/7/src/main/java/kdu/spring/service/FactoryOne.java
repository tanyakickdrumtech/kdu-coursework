package kdu.spring.service;
import kdu.spring.repository.InventoryStore;
import org.springframework.stereotype.Component;


@Component("factoryOne")
public class FactoryOne extends VehicleService {

    /**
     * constructor
     * @param tyreService
     * @param speakerService
     * @param inventoryStore
     */
    public FactoryOne(TyreService tyreService,SpeakerService speakerService,InventoryStore inventoryStore) {
        super(tyreService,speakerService,inventoryStore);
    }

    /**
     * to generate the random price for the vehicle
     * @return
     */
    public double generatePrice(){
        return 1.1 * Math.random() * 100000;
    }
}
