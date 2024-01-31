package kdu.backend.spring.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
public class UserShiftDto {
    private UUID id;
    private UUID shiftId;
    private UUID userId;
    private UUID tenantId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String createdBy;
    private String updatedBy;

}
