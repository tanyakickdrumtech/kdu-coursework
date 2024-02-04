package com.kdu.smarthome.response;

import com.kdu.smarthome.entities.Inventory;
import lombok.Data;

@Data
public class InventoryResponse {

    private String message;
    private Inventory inventory;

    public InventoryResponse(String message, Inventory inventory) {
        this.message = message;
        this.inventory = inventory;
    }
}
