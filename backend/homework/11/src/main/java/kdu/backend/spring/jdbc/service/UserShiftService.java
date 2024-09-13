package kdu.backend.spring.jdbc.service;

import kdu.backend.spring.jdbc.dao.UserShiftDao;
import kdu.backend.spring.jdbc.model.UserShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserShiftService {

    private UserShiftDao userShiftDao;

    @Autowired
    public UserShiftService(UserShiftDao userShiftDao){
        this.userShiftDao=userShiftDao;
    }

    /**
     * method to save user shift
     * @param userShift
     */
    public void saveUserShift(UserShift userShift) {
        userShiftDao.saveUserShift(userShift);
    }

    /**
     * returning list of user by id
     * @param tenantId
     * @return
     */
    public List<UserShift> getUserShiftById(UUID tenantId) {
        return userShiftDao.getUserShiftsByUserId(tenantId);
    }
}


