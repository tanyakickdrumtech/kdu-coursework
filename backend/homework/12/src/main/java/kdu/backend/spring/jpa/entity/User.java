package kdu.backend.spring.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "user")
public class User extends DefaultRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private int loggedIn;
    private String timeZone;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
