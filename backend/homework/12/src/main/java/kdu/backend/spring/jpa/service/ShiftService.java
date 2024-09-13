package kdu.backend.spring.jpa.service;

import kdu.backend.spring.jpa.dao.ShiftDao;
import kdu.backend.spring.jpa.entity.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ShiftService {
    /**
     * various methods that are used from controller classes to call api which used these methods and call the dao classes
     */
    private final ShiftDao shiftDao;

    @Autowired
    public ShiftService(ShiftDao shiftDao) {
        this.shiftDao = shiftDao;
    }

    public void saveShift(Shift shift) {
        shiftDao.save(shift);
    }

    public Shift getShiftById(UUID shiftId) {
        return shiftDao.findById(shiftId).orElse(null);
    }

    public List<Shift> getAllShifts() {
        return shiftDao.findAll();
    }

    public List<Shift> findTopThreeShiftsByDateRange(Date startDate, Date endDate) {
        return shiftDao.findTopThreeShiftsByDateRange(startDate, endDate);
    }
}