package com.kdu.smarthome.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory extends DefaultTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kickston_id", unique = true, nullable = false)
    @JsonProperty("kickston_id")
    private String kickstonId;

    @Column(name = "device_username", nullable = false)
    @JsonProperty("device_username")
    private String deviceUsername;

    @Column(name = "device_password", nullable = false)
    @JsonProperty("device_password")
    private String devicePassword;

    @Column(name = "manufacture_date_time", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonProperty("manufacture_date_time")
    private Timestamp manufactureDateTime;

    @Column(name = "manufacture_factory_place")
    @JsonProperty("manufacture_factory_place")
    private String manufactureFactoryPlace;
}

