package kdu.backend.spring.jdbc.dao;

import kdu.backend.spring.jdbc.model.UserShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserShiftDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserShiftDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * method to add user shift
     * @param userShift
     */
    public void saveUserShift(UserShift userShift) {
        String sql = "INSERT INTO shift_users(id, shift_id, user_id, tenant_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                UUID.randomUUID(),
                userShift.getShiftId(),
                userShift.getUserId(),
                userShift.getTenantId()
        );
    }

    /**
     * method to add user shift
     * @param tenantId
     * @return
     */
    public List<UserShift> getUserShiftsByUserId(UUID tenantId) {
        String sql = "SELECT * FROM shift_users WHERE tenant_id = ?";

        return jdbcTemplate.query(sql, new Object[]{tenantId}, new BeanPropertyRowMapper<>(UserShift.class));
    }

}

