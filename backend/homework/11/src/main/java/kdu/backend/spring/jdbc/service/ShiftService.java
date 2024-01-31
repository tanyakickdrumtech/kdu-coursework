package kdu.backend.spring.jdbc.service;

import kdu.backend.spring.jdbc.dao.ShiftDao;
import kdu.backend.spring.jdbc.exception.MyCustomException;
import kdu.backend.spring.jdbc.model.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ShiftService {

    private ShiftDao shiftDao;

    @Autowired
    public ShiftService(ShiftDao shiftDao){
        this.shiftDao=shiftDao;
    }

    /**
     * doing transaction and saving the shift data
     * @param shift
     * @throws MyCustomException
     */
    @Transactional
    public void saveShift(Shift shift) throws MyCustomException {
        try {
            shiftDao.saveShift(shift);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save shift.");
        }
    }

    /**
     * get shift using tenant id
     * @param tenantId
     * @return
     * @throws MyCustomException
     */
    public Shift getShift(UUID tenantId) throws MyCustomException {
        return shiftDao.getShiftById(tenantId);
    }
}


