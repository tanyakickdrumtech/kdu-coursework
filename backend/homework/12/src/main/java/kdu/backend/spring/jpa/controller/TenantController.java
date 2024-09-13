package kdu.backend.spring.jpa.controller;

import kdu.backend.spring.jpa.entity.Tenant;
import kdu.backend.spring.jpa.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TenantController {
    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    /**
     * getting a tennat
     * @param name
     * @return
     */
    @PostMapping("/tenant")
    public ResponseEntity<String> createTenant(@RequestBody String name) {
        tenantService.addTenant(name);
        return new ResponseEntity<>("Tenant created successfully", HttpStatus.CREATED);
    }

    /**
     * getting all tenants
     * @return
     */
    @GetMapping("/tenant/all")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> allTenants = tenantService.getAllTenant();
        return new ResponseEntity<>(allTenants, HttpStatus.OK);
    }
}
