package com.kdu.smarthome.response;

import com.kdu.smarthome.entities.Room;

public class RoomResponse {

    private String message;
    private Room room;

    public RoomResponse(String message, Room room) {
        this.message = message;
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public Room getRoom() {
        return room;
    }
}

