package com.kdu.smarthome.service;

import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class providing business logic for user-related operations.
 */
@Service
public class UserService {

    private UserRepository userRepository;

    /**
     * Constructs an instance of UserService.
     *
     * @param userRepository UserRepository for database interactions with User entities
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return List of all users in the system
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param userId ID of the user to retrieve
     * @return Optional containing the user if found, empty otherwise
     */
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * Creates a new user.
     *
     * @param user User entity representing the new user
     * @return User entity representing the created user
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId ID of the user to delete
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username Username of the user to retrieve
     * @return User entity representing the user with the specified username
     */
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }
}
