package com.kdu.smarthome.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Bean class representing the request to register a house.
 */
@Data
public class HouseRegisterRequestBean {

    /**
     * The address of the house.
     */
    private String address;

    /**
     * The name of the house.
     */
    private String houseName;

    /**
     * Constructs an instance of {@code HouseRegisterRequestBean} with the specified address and house name.
     *
     * @param address    The address of the house.
     * @param houseName  The name of the house.
     */
    @JsonCreator
    public HouseRegisterRequestBean(@JsonProperty("address") String address, @JsonProperty("house_name") String houseName) {
        this.address = address;
        this.houseName = houseName;
    }
}
