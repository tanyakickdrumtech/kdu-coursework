package com.example.springassessment2.service;

import com.example.springassessment2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for adding startup data to the application.
 */
@Service
public class StartUpDataAddition implements CommandLineRunner {

    UserService userService;

    PasswordEncoder passwordEncoder;

    @Autowired
    public StartUpDataAddition(UserService userService,PasswordEncoder passwordEncoder){
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
    }

    /**
     * Adds startup data to the application.
     *
     * @param args The command line arguments (not used).
     * @throws Exception If an exception occurs during startup data addition.
     */
    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setFullName("Rahul");
        user.setUsername("rahul");
        user.setEmail("rahul@gmail.com");
        user.setPassword(passwordEncoder.encode("Testing123"));
        user.setRole("ROLE_ADMIN");
        userService.saveUser(user);

        user.setFullName("Ajay");
        user.setUsername("ajay");
        user.setEmail("ajay@gmail.com");
        user.setPassword(passwordEncoder.encode("Testing123"));
        user.setRole("ROLE_BASIC");
        userService.saveUser(user);
    }
}
