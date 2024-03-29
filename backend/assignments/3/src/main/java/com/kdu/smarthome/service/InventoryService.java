package com.kdu.smarthome.service;

import com.kdu.smarthome.entities.Inventory;
import com.kdu.smarthome.repository.InventoryRepository;
import com.kdu.smarthome.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing operations related to Inventory items.
 */
@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    /**
     * Constructs an instance of InventoryService.
     *
     * @param inventoryRepository InventoryRepository for database interactions with Inventory entities
     */
    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Retrieves a list of all Inventory items in the system.
     *
     * @return The response message along with the details of the first Inventory item in the list
     */
    public InventoryResponse getAllInventoryItems() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return new InventoryResponse("Inventory items retrieved successfully.", inventories.get(0));
    }

    /**
     * Adds a new Inventory item to the system.
     *
     * @param request Inventory object containing details of the new item
     * @return The response message along with the added Inventory item
     */
    public InventoryResponse addInventoryItem(Inventory request) {
        Inventory inventory = new Inventory();
        inventory.setKickstonId(request.getKickstonId());
        inventory.setDeviceUsername(request.getDeviceUsername());
        inventory.setDevicePassword(request.getDevicePassword());
        inventory.setManufactureDateTime(request.getManufactureDateTime());
        inventory.setManufactureFactoryPlace(request.getManufactureFactoryPlace());
        Inventory savedItem = inventoryRepository.save(inventory);
        return new InventoryResponse("Inventory item added successfully.", savedItem);
    }
}
