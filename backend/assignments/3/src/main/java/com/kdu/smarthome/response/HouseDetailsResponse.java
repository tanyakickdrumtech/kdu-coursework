package com.kdu.smarthome.response;

import com.kdu.smarthome.entities.House;

public class HouseDetailsResponse {

    private String message;
    private House house;

    public HouseDetailsResponse(String message, House house) {
        this.message = message;
        this.house = house;
    }

    public String getMessage() {
        return message;
    }

    public House getHouse() {
        return house;
    }
}
