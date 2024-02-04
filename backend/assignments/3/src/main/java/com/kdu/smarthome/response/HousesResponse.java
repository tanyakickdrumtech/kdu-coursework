package com.kdu.smarthome.response;

import com.kdu.smarthome.entities.House;

import java.util.List;

public class HousesResponse {

    private String message;
    private List<House> houses;

    public HousesResponse(String message, List<House> houses) {
        this.message = message;
        this.houses = houses;
    }

    public String getMessage() {
        return message;
    }

    public List<House> getHouses() {
        return houses;
    }
}
