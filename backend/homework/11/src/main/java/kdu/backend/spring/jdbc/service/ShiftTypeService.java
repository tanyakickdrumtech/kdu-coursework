package kdu.backend.spring.jdbc.service;

import kdu.backend.spring.jdbc.dao.ShiftTypeDao;
import kdu.backend.spring.jdbc.model.ShiftType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShiftTypeService {

    private ShiftTypeDao shiftTypeDao;

    @Autowired
    public ShiftTypeService(ShiftTypeDao shiftTypeDao){
        this.shiftTypeDao=shiftTypeDao;
    }

    /**
     * method to add shift type
     * @param shiftType
     */
    public void saveShiftType(ShiftType shiftType) {
        shiftTypeDao.saveShiftType(shiftType);
    }

    /**
     * method to get shift id by id
     * @param shiftTypeId
     * @return
     */
    public ShiftType getShiftTypeById(UUID shiftTypeId) {
        return shiftTypeDao.getShiftTypeById(shiftTypeId);
    }

    /**
     * method to get shift
     * @param tenantId
     * @return
     */
    public ShiftType getShiftTypes(UUID tenantId) {
        return shiftTypeDao.getShiftTypeById(tenantId);
    }

}

