package kdu.backend.spring.jpa.service;

import kdu.backend.spring.jpa.dao.TenantDao;
import kdu.backend.spring.jpa.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantDao tenantDao;

    @Autowired
    public TenantService(TenantDao tenantDao) {
        this.tenantDao = tenantDao;
    }

    public void addTenant(String name) {
        Tenant tenant = new Tenant();
        tenant.setName(name);

        tenantDao.save(tenant);
    }

    public List<Tenant> getAllTenant() {
        return tenantDao.findAll();
    }
}