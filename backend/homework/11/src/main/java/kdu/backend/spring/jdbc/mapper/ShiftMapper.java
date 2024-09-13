package kdu.backend.spring.jdbc.mapper;

import kdu.backend.spring.jdbc.model.Shift;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ShiftMapper implements RowMapper<Shift> {

    @Override
    public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shift shift = new Shift();
        shift.setId(UUID.fromString(rs.getString("id")));
        shift.setShiftTypeId(UUID.fromString(rs.getString("shift_type_id")));
        shift.setName(rs.getString("name"));
        shift.setDateStart(rs.getDate("date_start"));
        shift.setDateEnd(rs.getDate("date_end"));
        shift.setTimeStart(rs.getTime("time_start"));
        shift.setTimeEnd(rs.getTime("time_end"));
        shift.setCreatedAt(rs.getTimestamp("created_at"));
        shift.setUpdatedAt(rs.getTimestamp("updated_at"));
        shift.setTimeZone(rs.getString("time_zone"));
        shift.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        shift.setCreatedBy(rs.getString("created_by"));
        shift.setCreatedBy(rs.getString("updated_by"));

        return shift;
    }
}

