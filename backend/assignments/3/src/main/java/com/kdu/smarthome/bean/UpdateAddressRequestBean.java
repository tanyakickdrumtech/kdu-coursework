package com.kdu.smarthome.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Bean class representing the request to update the address of a house.
 */
@Data
public class UpdateAddressRequestBean {

    /**
     * The new address to be set for the house.
     */
    private String address;

    /**
     * Constructs an instance of {@code UpdateAddressRequestBean} with the specified new address.
     *
     * @param address The new address to be set for the house.
     */
    @JsonCreator
    public UpdateAddressRequestBean(@JsonProperty("address") String address) {
        this.address = address;
    }
}
