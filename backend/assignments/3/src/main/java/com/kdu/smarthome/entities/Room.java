package com.kdu.smarthome.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rooms")
public class Room extends DefaultTags{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    @JsonBackReference
    private House house;

    @Column(name = "room_name", nullable = false)
    private String roomName;


    @OneToMany(mappedBy = "room")
    private Set<DeviceRoom> devicesRooms;
}

