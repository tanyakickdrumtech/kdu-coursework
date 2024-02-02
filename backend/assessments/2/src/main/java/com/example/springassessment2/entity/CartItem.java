package com.example.springassessment2.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart_items")
public class CartItem extends DefaultTags{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;


    @OneToMany(mappedBy = "id")
    private List<Product> productList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
