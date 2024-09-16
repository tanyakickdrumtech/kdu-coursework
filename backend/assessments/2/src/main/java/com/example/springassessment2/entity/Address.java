package com.example.springassessment2.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class Address extends DefaultTags{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

