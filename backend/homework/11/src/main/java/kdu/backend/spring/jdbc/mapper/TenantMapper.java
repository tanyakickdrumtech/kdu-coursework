package kdu.backend.spring.jdbc.mapper;

import kdu.backend.spring.jdbc.model.Tenant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TenantMapper implements RowMapper<Tenant> {

    @Override
    public Tenant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tenant tenant = new Tenant();
        tenant.setId(UUID.fromString(rs.getString("id")));
        tenant.setName(rs.getString("name"));
        tenant.setCreatedAt(rs.getTimestamp("created_at"));
        tenant.setUpdatedAt(rs.getTimestamp("updated_at"));
        tenant.setCreatedBy(rs.getString("created_by"));
        tenant.setCreatedBy(rs.getString("updated_by"));
        return tenant;
    }
}

