package kdu.backend.spring.jdbc.mapper;

import kdu.backend.spring.jdbc.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(UUID.fromString(rs.getString("id")));
        user.setTenantId(UUID.fromString(rs.getString("tenant_id")));
        user.setUserName(rs.getString("user_name"));
        user.setLoggedIn(rs.getInt("logged_in"));
        user.setTimeZone(rs.getString("time_zone"));
        user.setCreatedBy(rs.getString("created_by"));
        user.setUpdatedBy(rs.getString("updated_by"));
        user.setCreatedAt(rs.getTimestamp("created_at"));
        user.setUpdatedAt(rs.getTimestamp("updated_at"));

        return user;
    }
}
