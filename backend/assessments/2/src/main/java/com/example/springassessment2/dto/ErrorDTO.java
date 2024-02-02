package com.example.springassessment2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) class for representing error messages.
 */
@Data
@AllArgsConstructor
public class ErrorDTO {
    String message;
    int statusCode;
}
