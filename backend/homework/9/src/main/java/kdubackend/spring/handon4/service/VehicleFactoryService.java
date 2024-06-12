package kdubackend.spring.handon4.service;

import kdubackend.spring.handon4.repository.InventoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class VehicleFactoryService {
    private InventoryStore inventoryStore;

    @Autowired
    public VehicleFactoryService(InventoryStore inventoryStore){
        this.inventoryStore=inventoryStore;
    }

    public InventoryStore getInventoryStore() {
        return inventoryStore;
    }
}