package com.example.springassessment2.config;

import com.example.springassessment2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom UserDetailsService implementation for loading user details from the PersonService.
 */
@Component
public class PersonUserDetails implements UserDetailsService {

    @Autowired
    UserService personService;

    public PersonUserDetails(UserService personService){
        this.personService=personService;
    }

    /**
     * Loads user details by username.
     *
     * @param username The username of the user.
     * @return A UserDetails object representing the user details.
     * @throws UsernameNotFoundException If the user details are not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.springassessment2.entity.User person = personService.getUserByUsername(username);
        String personUserName = null;
        String personPassword = null;
        List<GrantedAuthority> authorities = null;

        if(person == null){
            throw new UsernameNotFoundException("User details not found for user : " + username + ". Please register first.");
        }else{
            personUserName = person.getUsername();
            personPassword = person.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(person.getRole()));
        }
        return new User(personUserName, personPassword, authorities);
    }
}
