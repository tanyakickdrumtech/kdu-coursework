package com.kdu.smarthome.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Bean class representing the request to add a user to a house.
 */
@Data
public class AddUserToHouseRequestBean {

    /**
     * The username of the user to be added to the house.
     */
    private String username;

    /**
     * Constructs an instance of {@code AddUserToHouseRequestBean} with the specified username.
     *
     * @param username The username of the user to be added to the house.
     */
    @JsonCreator
    public AddUserToHouseRequestBean(@JsonProperty("username") String username) {
        this.username = username;
    }
}
