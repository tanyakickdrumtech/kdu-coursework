package com.kdu.smarthome.service;

import com.kdu.smarthome.entities.House;
import com.kdu.smarthome.entities.Room;
import com.kdu.smarthome.exception.HouseNotFoundException;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import com.kdu.smarthome.response.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing operations related to Room entities.
 */
@Service
public class RoomService {

    private RoomRepository roomRepository;

    private HouseRepository houseRepository;

    /**
     * Constructs an instance of RoomService.
     *
     * @param roomRepository   RoomRepository for database interactions with Room entities
     * @param houseRepository  HouseRepository for database interactions with House entities
     */
    @Autowired
    public RoomService(RoomRepository roomRepository, HouseRepository houseRepository) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
    }

    /**
     * Adds a new Room to a specified House.
     *
     * @param houseId  The ID of the House to which the Room will be added
     * @param roomName The name of the new Room
     * @return The response message along with the details of the added Room
     * @throws HouseNotFoundException If the specified House is not found
     */
    public RoomResponse addRoomToHouse(Long houseId, String roomName) throws HouseNotFoundException {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException("House not found with id: " + houseId));

        Room room = new Room();
        room.setRoomName(roomName);
        room.setHouse(house);
        Room savedRoom = roomRepository.save(room);

        return new RoomResponse("Room added successfully.", savedRoom);
    }
}
