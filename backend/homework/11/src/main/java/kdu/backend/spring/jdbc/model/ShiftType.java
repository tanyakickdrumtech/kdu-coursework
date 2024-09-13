package kdu.backend.spring.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ShiftType {
    private UUID id;
    private String uqName;
    private String description;
    private boolean active;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String timeZone;
    private UUID tenantId;
    private String createdBy;
    private String updatedBy;

    public ShiftType() {
        /**
         * to call the constructor without parameter
         */
    }
}
