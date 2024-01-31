package kdu.backend.spring.jdbc.dao;

import kdu.backend.spring.jdbc.exception.MyCustomException;
import kdu.backend.spring.jdbc.mapper.UserMapper;
import kdu.backend.spring.jdbc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * method to save data in user table
     * @param user
     */
    public void saveUser(User user) {
        String sql = "INSERT INTO users (id, username, loggedIn, timeZone,tenant_id) VALUES (?, ?, ?, ?,?)";

        jdbcTemplate.update(sql,
                UUID.randomUUID(),
                user.getUserName(),
                user.getLoggedIn(),
                user.getTimeZone(),
                user.getTenantId()
        );
    }

    /**
     * method to get data from user
     * @param userId
     * @return
     * @throws MyCustomException
     */
    public User getUserById(UUID userId) throws MyCustomException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(User.class));
        } catch (Exception e) {
            throw new MyCustomException("Exception Occured");
        }
    }

    /**
     * method to get user
     * @param tenantId
     * @return
     */

    public List<User> getUsers(UUID tenantId) {
        String sql = "SELECT * FROM users WHERE tenant_id = ?";
        return jdbcTemplate.query(sql, new Object[]{tenantId}, new UserMapper());
    }
}
