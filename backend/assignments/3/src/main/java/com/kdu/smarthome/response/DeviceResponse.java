package com.kdu.smarthome.response;

import com.kdu.smarthome.entities.Device;

public class DeviceResponse {

    private String message;
    private Device device;

    public DeviceResponse(String message, Device device) {
        this.message = message;
        this.device = device;
    }

    public String getMessage() {
        return message;
    }

    public Device getDevice() {
        return device;
    }
}
