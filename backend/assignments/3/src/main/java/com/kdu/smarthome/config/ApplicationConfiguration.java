package com.kdu.smarthome.config;

import com.kdu.smarthome.repository.UserCredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for application-related beans.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    /**
     * Repository for user credentials.
     */
    private final UserCredentialsRepository userCredentialsRepository;

    /**
     * Configures the authentication manager.
     *
     * @param config The authentication configuration.
     * @return The configured authentication manager.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Provides a BCrypt password encoder.
     *
     * @return The BCrypt password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
