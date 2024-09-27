package com.example.security.controller;

import com.example.security.exceptions.EmptyListException;
import com.example.security.exceptions.ResourceNotFoundException;
import com.example.security.logging.LoggerDisplay;
import com.example.security.model.User;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * the code retrieves a list of users when a GET request is made to "/users", throws an exception if the list is empty,
     * logs the success, and returns the list of users in the response.
     * @return
     * @throws EmptyListException
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUserList() throws EmptyListException {
        List<User> users = userService.getUserList();
        if(users.isEmpty()){
            throw new EmptyListException("Empty List");
        }
        LoggerDisplay.logInfo("Retireved all users from list successfully");
        return ResponseEntity.ok(users);
    }

    /**
     * the code retrieves a user by their username when a GET request is made to "/user/{userName}",
     * logs the success, and returns the user information in the response.
     * @param userName
     * @return
     * @throws ResourceNotFoundException
     */

    @GetMapping("/user/{userName}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName) throws ResourceNotFoundException {
        User user = userService.getUserByUserName(userName);
        LoggerDisplay.logInfo("User Retrived Successfully");
        return ResponseEntity.ok(user);
    }

    /**
     *  the code adds a user to the system when a POST request
     *  with a User object in the request body is received at the "/user/admin" endpoint.
     * @param user
     * @return
     */
    @PostMapping("/user/admin")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        LoggerDisplay.logInfo("User Added SuccessFully");
        return ResponseEntity.ok("User is Added");
    }
}
