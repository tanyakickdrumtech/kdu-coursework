package com.kdu.smarthome.service;

import com.kdu.smarthome.entities.DeviceRoom;
import com.kdu.smarthome.repository.DeviceRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to DeviceRooms.
 */
@Service
public class DeviceRoomService {

    private DeviceRoomRepository deviceRoomRepository;

    /**
     * Constructs an instance of DeviceRoomService.
     *
     * @param deviceRoomRepository DeviceRoomRepository for database interactions with DeviceRoom entities
     */
    @Autowired
    public DeviceRoomService(DeviceRoomRepository deviceRoomRepository) {
        this.deviceRoomRepository = deviceRoomRepository;
    }

    /**
     * Retrieves a list of all DeviceRooms in the system.
     *
     * @return List of DeviceRooms
     */
    public List<DeviceRoom> getAllDeviceRooms() {
        return deviceRoomRepository.findAll();
    }

    /**
     * Retrieves a DeviceRoom by its ID.
     *
     * @param deviceRoomId ID of the DeviceRoom
     * @return Optional containing the DeviceRoom, or empty if not found
     */
    public Optional<DeviceRoom> getDeviceRoomById(Long deviceRoomId) {
        return deviceRoomRepository.findById(deviceRoomId);
    }

    /**
     * Creates a new DeviceRoom in the system.
     *
     * @param deviceRoom DeviceRoom object to be created
     * @return The created DeviceRoom
     */
    public DeviceRoom createDeviceRoom(DeviceRoom deviceRoom) {
        return deviceRoomRepository.save(deviceRoom);
    }

    /**
     * Deletes a DeviceRoom by its ID.
     *
     * @param deviceRoomId ID of the DeviceRoom to be deleted
     */
    public void deleteDeviceRoom(Long deviceRoomId) {
        deviceRoomRepository.deleteById(deviceRoomId);
    }
}
