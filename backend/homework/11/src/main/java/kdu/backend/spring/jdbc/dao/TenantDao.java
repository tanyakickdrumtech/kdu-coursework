package kdu.backend.spring.jdbc.dao;

import kdu.backend.spring.jdbc.mapper.TenantMapper;
import kdu.backend.spring.jdbc.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class TenantDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TenantDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * method to save tenant data
     * @param tenant
     */
    public void saveTenant(Tenant tenant) {
        String sql = "INSERT INTO tenant (id, name, description, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                UUID.randomUUID(),
                tenant.getName(),
                tenant.getCreatedAt(),
                tenant.getUpdatedAt(),
                tenant.getCreatedBy(),
                tenant.getUpdatedBy()
        );
    }

    /**
     * method to get tenant data by id
     * @param tenantId
     * @return
     */
    public Tenant getTenantById(UUID tenantId) {
        String sql = "SELECT * FROM tenant WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{tenantId}, new TenantMapper());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * method to get all tenant
     * @return
     */
    public List<Tenant> getAllTenants() {
        String sql = "SELECT * FROM tenants";
        return jdbcTemplate.query(sql, new TenantMapper());
    }
}

