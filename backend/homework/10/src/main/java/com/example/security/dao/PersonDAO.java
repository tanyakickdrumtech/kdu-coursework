package com.example.security.dao;


import com.example.security.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonDAO {
    List<Person> personList;

    public PersonDAO() {
        this.personList = new ArrayList<>();
    }

    public void addPerson(Person person){
        personList.add(person);
    }

    public List<Person> getAllPersons(){
        return personList;
    }
}

