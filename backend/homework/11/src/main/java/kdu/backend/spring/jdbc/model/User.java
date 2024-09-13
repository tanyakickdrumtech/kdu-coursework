package kdu.backend.spring.jdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
public class User {
    private UUID id;
    private UUID tenantId;
    private String userName;
    private Integer loggedIn;
    private String timeZone;
    private String createdBy;
    private String updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User() {
        /**
         * to call the constructor without parameter
         */
    }
}
