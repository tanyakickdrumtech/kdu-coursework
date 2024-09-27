package com.example.security.repository;
import com.example.security.logging.LoggerDisplay;
import com.example.security.model.User;
import com.example.security.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepo {

    List<User> userList = new ArrayList<>();

    /**
     * method to add user to the list
     * @param user
     */
    public void addUser(User user) {
        userList.add(user);
    }

    /**
     * returning the list with all list
     * @return
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * this method is used to get the user by the username
     * @param userName
     * @return
     * @throws ResourceNotFoundException
     */
    public User getUserByUserName(String userName) throws ResourceNotFoundException {
        User userOne = userList.stream()
                .filter(user -> user.getUserName().equals(userName))
                .findFirst()
                .get();
        if (userOne == null) {
            LoggerDisplay.logError("User Retrieval Unsuccessfull");
            throw new ResourceNotFoundException("User not found with the given username");
        }
        return userOne;
    }

}
