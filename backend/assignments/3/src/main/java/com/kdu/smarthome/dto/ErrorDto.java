package com.kdu.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class for representing error messages.
 */
@Data
@AllArgsConstructor
public class ErrorDto {
    /**
     * The error message.
     */
    String message;

    /**
     * The status code associated with the error.
     */
    int statusCode;
}
