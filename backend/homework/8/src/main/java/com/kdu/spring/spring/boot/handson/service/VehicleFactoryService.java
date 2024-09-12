package com.kdu.spring.spring.boot.handson.service;

import com.kdu.spring.spring.boot.handson.repository.InventoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
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
