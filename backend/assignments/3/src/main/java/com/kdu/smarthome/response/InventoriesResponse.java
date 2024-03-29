package com.kdu.smarthome.response;

import com.kdu.smarthome.entities.Inventory;
import lombok.Data;

import java.util.List;

@Data
public class InventoriesResponse {

    private String message;
    private List<Inventory> inventory;

    public InventoriesResponse(String message, List<Inventory> inventory) {
        this.message = message;
        this.inventory = inventory;
    }
}
