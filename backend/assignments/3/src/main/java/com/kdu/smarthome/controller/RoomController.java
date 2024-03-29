package com.kdu.smarthome.controller;

import com.kdu.smarthome.bean.AddRoomRequestBean;
import com.kdu.smarthome.exception.HouseNotFoundException;
import com.kdu.smarthome.response.RoomResponse;
import com.kdu.smarthome.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class handling room-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    /**
     * Constructor for RoomController.
     *
     * @param roomService The room service.
     */
    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Endpoint to add a new room to a house.
     *
     * @param houseId             The identifier of the house to which the room will be added.
     * @param addRoomRequestBean  The details of the room to be added.
     * @return ResponseEntity with RoomResponse containing details of the added room.
     * @throws HouseNotFoundException If the specified house is not found.
     */
    @PostMapping
    public ResponseEntity<RoomResponse> addRoomToHouse(@RequestParam String houseId, @RequestBody AddRoomRequestBean addRoomRequestBean) throws HouseNotFoundException {
        return ResponseEntity.ok(roomService.addRoomToHouse(Long.parseLong(houseId), addRoomRequestBean.getRoomName()));
    }
}
