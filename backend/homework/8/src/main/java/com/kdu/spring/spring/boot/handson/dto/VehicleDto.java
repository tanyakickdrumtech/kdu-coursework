package com.kdu.spring.spring.boot.handson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDto {

    private String name;
    private double price;
    private long id;
}

