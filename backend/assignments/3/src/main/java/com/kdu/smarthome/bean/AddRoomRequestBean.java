package com.kdu.smarthome.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Bean class representing the request to add a room.
 */
@Data
public class AddRoomRequestBean {

    /**
     * The name of the room to be added.
     */
    private String roomName;

    /**
     * Constructs an instance of {@code AddRoomRequestBean} with the specified room name.
     *
     * @param roomName The name of the room to be added.
     */
    @JsonCreator
    public AddRoomRequestBean(@JsonProperty("room_name") String roomName) {
        this.roomName = roomName;
    }
}
