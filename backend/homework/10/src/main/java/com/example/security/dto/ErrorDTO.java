package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
public class ErrorDTO {
    private String message;
    private HttpStatus httpStatus;
}
