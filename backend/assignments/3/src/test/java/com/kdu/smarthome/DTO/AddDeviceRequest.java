package com.kdu.smarthome.DTO;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class AddDeviceRequest {


    private String houseId;

    private String roomId;

    private String kickstonId;


}

