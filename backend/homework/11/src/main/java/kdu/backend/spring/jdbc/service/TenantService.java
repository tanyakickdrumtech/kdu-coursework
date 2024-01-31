package kdu.backend.spring.jdbc.service;

import kdu.backend.spring.jdbc.dao.TenantDao;
import kdu.backend.spring.jdbc.dto.AllDataDto;
import kdu.backend.spring.jdbc.exception.MyCustomException;
import kdu.backend.spring.jdbc.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantService {

    private final TenantDao tenantDao;
    private final ShiftService shiftService;
    private final UserService userService;
    private final ShiftTypeService shiftTypeService;
    private final UserShiftService userShiftService;

    @Autowired
    public TenantService(
            TenantDao tenantDao,
            ShiftService shiftService,
            UserService userService,
            ShiftTypeService shiftTypeService,
            UserShiftService userShiftService
    ) {
        this.tenantDao = tenantDao;
        this.shiftService = shiftService;
        this.userService = userService;
        this.shiftTypeService = shiftTypeService;
        this.userShiftService = userShiftService;
    }

    /**
     * method to get all tenants
     * @return
     */

    public List<Tenant> getAllTenants() {
        return tenantDao.getAllTenants();
    }

    /**
     * method to save all the tenant data
     * @param tenantData
     * @throws MyCustomException
     */
    @Transactional
    public void saveTenantData(AllDataDto tenantData) throws MyCustomException {
        tenantDao.saveTenant(tenantData.getTenant());

        shiftService.saveShift(tenantData.getShift());
        userService.saveUser(tenantData.getUser());
        shiftTypeService.saveShiftType(tenantData.getShiftType());
        userShiftService.saveUserShift(tenantData.getShiftUser());
    }
}
