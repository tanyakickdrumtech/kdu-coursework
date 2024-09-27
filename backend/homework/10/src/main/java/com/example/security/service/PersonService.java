package com.example.security.service;

import com.example.security.model.Person;
import com.example.security.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonDAO personDAO;

    @Autowired
    public PersonService(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    public Person getPersonUserName(String name){
        for(Person person: personDAO.getAllPersons()){
            if(person.getUsername().equals(name)){
                return person;
            }
        }
        return null;
    }
}