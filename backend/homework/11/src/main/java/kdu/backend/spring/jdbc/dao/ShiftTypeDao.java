package kdu.backend.spring.jdbc.dao;

import kdu.backend.spring.jdbc.model.ShiftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public class ShiftTypeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShiftTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * method to save shift type
     * @param shiftType
     */
    public void saveShiftType(ShiftType shiftType) {
        String sql = "INSERT INTO shift_types (id, uq_name, description, active, created_at, updated_at, time_zone, tenant_id,created_by,updated_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                UUID.randomUUID(),
                shiftType.getUqName(),
                shiftType.getDescription(),
                shiftType.isActive(),
                shiftType.getCreatedAt(),
                shiftType.getUpdatedAt(),
                shiftType.getTimeZone(),
                shiftType.getTenantId(),
                shiftType.getCreatedBy(),
                shiftType.getUpdatedBy()
        );
    }

    public ShiftType getShiftTypeById(UUID tenantId) {
        String sql = "SELECT * FROM shift_types WHERE tenant_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{tenantId}, new BeanPropertyRowMapper<>(ShiftType.class));
        } catch (Exception e) {
            return null;
        }
    }
}

