package com.kdu.smarthome.response;

import com.kdu.smarthome.entities.House;

public class HouseResponse {

    private String message;
    private House house;

    public HouseResponse(String message, House house) {
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

