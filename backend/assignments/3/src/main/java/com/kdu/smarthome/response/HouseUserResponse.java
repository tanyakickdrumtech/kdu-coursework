package com.kdu.smarthome.response;

public class HouseUserResponse {

    private String message;
    private String username;

    public HouseUserResponse(String message, String username) {
        this.message = message;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }
}

