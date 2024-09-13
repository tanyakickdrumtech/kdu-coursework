package kdu.backend.spring.jpa.service;

import kdu.backend.spring.jpa.dao.UserDao;
import kdu.backend.spring.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void saveUser(User user) {
        userDao.save(user);
    }

    public User getUserById(UUID userId) {
        return userDao.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public Page<User> getAllUsersInPage(Pageable pageable){
        return userDao.findAll(pageable);
    }

    public void updateUserDetails(UUID userId, String username, int loggedIn, String timeZone) {
        userDao.updateUserDetails(userId, username, loggedIn, timeZone);
    }
}