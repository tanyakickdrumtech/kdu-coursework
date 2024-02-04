package com.kdu.smarthome.controller;

import com.kdu.smarthome.entities.Inventory;
import com.kdu.smarthome.response.InventoryResponse;
import com.kdu.smarthome.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class handling inventory-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * Constructor for InventoryController.
     *
     * @param inventoryService The inventory service.
     */
    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Endpoint to retrieve a list of all inventory items.
     *
     * @return ResponseEntity with InventoryResponse containing details of all inventory items.
     */
    @GetMapping
    public ResponseEntity<InventoryResponse> getAllInventoryItems() {
        return ResponseEntity.ok(inventoryService.getAllInventoryItems());
    }

    /**
     * Endpoint to add a new item to the inventory.
     *
     * @param request The details of the item to be added to the inventory.
     * @return ResponseEntity with InventoryResponse containing details of the added item.
     */
    @PostMapping
    public ResponseEntity<InventoryResponse> addItemToInventory(@RequestBody Inventory request) {
        return ResponseEntity.ok(inventoryService.addInventoryItem(request));
    }
}
