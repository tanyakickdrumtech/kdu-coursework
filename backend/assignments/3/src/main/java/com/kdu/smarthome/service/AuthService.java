package com.kdu.smarthome.service;

import com.kdu.smarthome.config.JwtService;
import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for handling user authentication and registration.
 */
@Service
public class AuthService {

    private UserDetailsService userDetailsService;
    private JwtService jwtService;
    private UserRepository userRepository;

    /**
     * Constructs an instance of AuthService.
     *
     * @param userDetailsService UserDetailsService for handling user details
     * @param jwtService         JwtService for handling JWT token operations
     * @param userRepository     UserRepository for database interactions with User entities
     */
    @Autowired
    public AuthService(UserDetailsService userDetailsService, JwtService jwtService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user in the system.
     *
     * @param registerRequest User object containing registration details
     * @return AuthResponse with a success message and JWT token
     */
    public AuthResponse registerUser(User registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setName(registerRequest.getFirstName() + " " + registerRequest.getLastName());
        user.setEmailId(registerRequest.getEmailId());

        userRepository.save(user);

        String jwtToken = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(registerRequest.getUsername());
            jwtToken = jwtService.generateToken(userDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new AuthResponse("User registered successfully.", jwtToken);
    }
}
