package kdu.backend.spring.jdbc.mapper;

import kdu.backend.spring.jdbc.model.UserShift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserShiftMapper implements RowMapper<UserShift> {

    @Override
    public UserShift mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserShift userShift = new UserShift();
        userShift.setId(UUID.fromString(rs.getString("id")));
        userShift.setShiftId(UUID.fromString(rs.getString("shift_id")));
        userShift.setUserId(UUID.fromString(rs.getString("user_id")));
        userShift.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        userShift.setCreatedAt(rs.getTimestamp("created_at"));
        userShift.setUpdatedAt(rs.getTimestamp("updated_at"));
        userShift.setCreatedBy(rs.getString("created_by"));
        userShift.setUpdatedBy(rs.getString("updated_by"));


        return userShift;
    }
}

