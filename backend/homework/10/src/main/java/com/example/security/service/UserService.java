package com.example.security.service;

import com.example.security.exceptions.ResourceNotFoundException;
import com.example.security.model.User;
import com.example.security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    UserRepo userRepo;

    @Autowired
    public UserService(UserRepo repo) {
        this.userRepo = repo;
    }

    public List<User> getUserList() {

        return userRepo.getUserList();
    }

    public User getUserByUserName(String userName) throws ResourceNotFoundException {
        return userRepo.getUserByUserName(userName);
    }

    public void addUser(User user) {
        userRepo.addUser(user);
    }
}
