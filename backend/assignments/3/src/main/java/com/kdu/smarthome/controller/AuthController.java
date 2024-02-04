package com.kdu.smarthome.controller;

import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.exception.EmailAlreadyExistsException;
import com.kdu.smarthome.exception.UsernameAlreadyExistsException;
import com.kdu.smarthome.response.AuthResponse;
import com.kdu.smarthome.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Constructor for AuthController.
     *
     * @param authService The authentication service.
     */
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint to register a new user.
     *
     * @param registerRequest The registration request containing user details.
     * @return ResponseEntity with AuthResponse containing a success message and authentication token.
     * @throws UsernameAlreadyExistsException If the provided username already exists.
     * @throws EmailAlreadyExistsException    If the provided email already exists.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User registerRequest)
            throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        String token = authService.registerUser(registerRequest).getToken();
        return ResponseEntity.ok(new AuthResponse("User registered successfully.", token));
    }
}
