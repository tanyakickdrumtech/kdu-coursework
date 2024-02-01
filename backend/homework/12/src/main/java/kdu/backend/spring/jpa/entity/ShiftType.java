package kdu.backend.spring.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "shift_type")
public class ShiftType {
    @Id
    private UUID id;
    public enum Type{
        MORNING, AFTERNOON, EVENING
    }
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Enumerated(EnumType.STRING)
    private Type uqName;
    private String description;
    private boolean active;
    private String timeZone;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;
}
