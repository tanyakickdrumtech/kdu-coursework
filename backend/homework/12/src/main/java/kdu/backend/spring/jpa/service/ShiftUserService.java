package kdu.backend.spring.jpa.service;

import kdu.backend.spring.jpa.dao.ShiftUserDao;
import kdu.backend.spring.jpa.entity.ShiftUser;
import kdu.backend.spring.jpa.exception.MyCustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Service
public class ShiftUserService {
    private final ShiftUserDao shiftUserDao;

    @Autowired
    public ShiftUserService(ShiftUserDao shiftUserDao) {
        this.shiftUserDao = shiftUserDao;
    }

    public void saveShiftUser(ShiftUser shiftUser) {
        shiftUserDao.save(shiftUser);
    }

    public ShiftUser getShiftUserById(UUID shiftUserId) {
        return shiftUserDao.findById(shiftUserId).orElse(null);
    }

    public List<ShiftUser> getAllShiftUsers() {
        return shiftUserDao.findAll();
    }

    public void deleteShiftUser(UUID shiftUserId) throws MyCustomException {
        List<ShiftUser> shiftUsers = shiftUserDao.findByShiftEndTimeAndId(Time.valueOf("23:00"), shiftUserId);

        if (!shiftUsers.isEmpty()) {
            shiftUserDao.deleteById(shiftUserId);
        } else {
            throw new MyCustomException("Exception Occured while finding the user");
        }
    }
}
