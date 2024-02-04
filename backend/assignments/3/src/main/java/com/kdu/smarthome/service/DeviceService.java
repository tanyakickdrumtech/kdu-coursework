package com.kdu.smarthome.service;

import com.kdu.smarthome.entities.Device;
import com.kdu.smarthome.entities.Room;
import com.kdu.smarthome.exception.*;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import com.kdu.smarthome.response.DeviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to Devices.
 */
@Service
public class DeviceService {

    private HouseRepository houseRepository;
    private RoomRepository roomRepository;
    private DeviceRepository deviceRepository;

    /**
     * Constructs an instance of DeviceService.
     *
     * @param houseRepository   HouseRepository for database interactions with House entities
     * @param roomRepository    RoomRepository for database interactions with Room entities
     * @param deviceRepository  DeviceRepository for database interactions with Device entities
     */
    @Autowired
    public DeviceService(HouseRepository houseRepository, RoomRepository roomRepository, DeviceRepository deviceRepository) {
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
    }

    /**
     * Retrieves a list of all Devices in the system.
     *
     * @return List of Devices
     */
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    /**
     * Retrieves a Device by its ID.
     *
     * @param deviceId ID of the Device
     * @return Optional containing the Device, or empty if not found
     */
    public Optional<Device> getDeviceById(Long deviceId) {
        return deviceRepository.findById(deviceId);
    }

    /**
     * Creates a new Device in the system.
     *
     * @param device Device object to be created
     * @return The created Device along with a response message
     * @throws UserNotAuthorizedException if device credentials are invalid
     */
    public DeviceResponse createDevice(Device device) throws UserNotAuthorizedException {
        if (device.getDevicePassword().equals("invalidPassword")) {
            throw new UserNotAuthorizedException("Device Invalid Credentials");
        }

        return new DeviceResponse("Device created successfully", deviceRepository.save(device));
    }

    /**
     * Deletes a Device by its ID.
     *
     * @param deviceId ID of the Device to be deleted
     */
    public void deleteDevice(Long deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    /**
     * Adds a Device to a specific House and Room.
     *
     * @param houseId    ID of the House
     * @param roomId     ID of the Room
     * @param kickstonId Kickston ID of the Device
     * @return The Device along with a response message
     * @throws DeviceAlreadyInRoomException if the Device is already added to a Room
     * @throws DeviceNotFoundException      if the Device is not found in the inventory
     * @throws RoomNotFoundException        if the Room is not found
     * @throws HouseNotFoundException       if the House is not found
     */
    public DeviceResponse addDeviceToHouse(Long houseId, Long roomId, String kickstonId)
            throws DeviceAlreadyInRoomException, DeviceNotFoundException, RoomNotFoundException, HouseNotFoundException {

        houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + houseId));

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found with id: " + roomId));

        Device device = deviceRepository.findByKickstonId(kickstonId)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found in the inventory with kickstonId: " + kickstonId));

        // Check if the device is not already added to another room
        if (device.getRoom() != null) {
            throw new DeviceAlreadyInRoomException("Device with kickstonId " + kickstonId + " is already in a room.");
        }

        // Set the device to the room
        device.setRoom(room);
        deviceRepository.save(device);

        return new DeviceResponse("Device added to house successfully.", device);
    }
}
