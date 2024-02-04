package com.kdu.smarthome.service;

import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.entities.UserPrincipal;
import com.kdu.smarthome.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service class implementing the UserDetailsService interface to provide user details for authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs an instance of UserDetailsServiceImpl.
     *
     * @param userRepository UserRepository for database interactions with User entities
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads user details by username for authentication purposes.
     *
     * @param username The username of the user to load details for
     * @return UserDetails representing the loaded user details
     * @throws UsernameNotFoundException If the user with the specified username is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserPrincipal.create(user);
    }
}
