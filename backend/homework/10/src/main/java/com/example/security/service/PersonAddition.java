package com.example.security.service;

import com.example.security.model.Person;
import com.example.security.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonAddition implements CommandLineRunner {

    private final PersonDAO personDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonAddition(PersonDAO personDAO, PasswordEncoder passwordEncoder) {
        this.personDAO = personDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        personDAO.addPerson(new Person("Tarun", "tarunAdmin", passwordEncoder.encode("Tarun123"), "ROLE_ADMIN"));
        personDAO.addPerson(new Person("Tanay", "tanayBasic", passwordEncoder.encode("Tanay@1234"), "ROLE_BASIC"));
    }
}
