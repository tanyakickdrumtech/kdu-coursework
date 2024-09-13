package kdu.backend.spring.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "shift_user")
public class ShiftUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Shift shift;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}