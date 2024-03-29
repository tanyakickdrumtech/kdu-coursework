package com.kdu.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing authentication information.
 */
@Data
@AllArgsConstructor
public class AuthDto {

    /**
     * The username associated with the authentication.
     */
    String username;

    /**
     * The password associated with the authentication.
     */
    String password;
}
