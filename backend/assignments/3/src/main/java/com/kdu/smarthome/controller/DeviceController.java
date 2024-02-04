package com.kdu.smarthome.controller;

import com.kdu.smarthome.entities.Device;
import com.kdu.smarthome.exception.*;
import com.kdu.smarthome.response.DeviceResponse;
import com.kdu.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class handling device-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    /**
     * Constructor for DeviceController.
     *
     * @param deviceService The device service.
     */
    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * Endpoint to register a new device.
     *
     * @param device The device details.
     * @return ResponseEntity with DeviceResponse containing details of the registered device.
     * @throws UserNotAuthorizedException If the user is not authorized to perform the operation.
     */
    @PostMapping("/register")
    public ResponseEntity<DeviceResponse> registerDevice(@RequestBody Device device) throws UserNotAuthorizedException {
        return ResponseEntity.ok(deviceService.createDevice(device));
    }

    /**
     * Endpoint to add a device to a house.
     *
     * @param houseId    The identifier of the house.
     * @param roomId     The identifier of the room.
     * @param kickstonId The identifier of the device.
     * @return ResponseEntity with DeviceResponse containing details of the added device.
     * @throws DeviceAlreadyInRoomException If the device is already present in the room.
     * @throws RoomNotFoundException       If the specified room is not found.
     * @throws DeviceNotFoundException     If the specified device is not found.
     * @throws HouseNotFoundException      If the specified house is not found.
     */
    @PostMapping("/add")
    public ResponseEntity<DeviceResponse> addDeviceToHouse(@RequestParam String houseId,
                                                           @RequestParam String roomId,
                                                           @RequestParam String kickstonId)
            throws DeviceAlreadyInRoomException, RoomNotFoundException, DeviceNotFoundException, HouseNotFoundException {
        return ResponseEntity.ok(deviceService.addDeviceToHouse(Long.parseLong(houseId), Long.parseLong(roomId), kickstonId));
    }
}
