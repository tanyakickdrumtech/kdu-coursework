package com.kdu.smarthome.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "devices")
public class Device extends DefaultTags {

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

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    // Constructors, getters, setters, equals, hashCode, etc.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(id, device.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

