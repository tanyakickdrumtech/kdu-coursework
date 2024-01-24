package com.kdu.spring.spring.boot.handson.entities;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Vehicle {
    private String name;
    private double price;
    private long id;
}
