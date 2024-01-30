package com.example.security.config;

import com.example.security.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.security.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CustomAuthenticationManager implements AuthenticationProvider {

    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomAuthenticationManager(PersonService personService, PasswordEncoder passwordEncoder) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * the code authenticates a user by checking if the provided credentials match those stored in the system.
     * It throws exceptions for unregistered users or invalid passwords and
     * returns an authenticated UsernamePasswordAuthenticationToken for valid credentials.
     * @param authentication the authentication request object.
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Optional<Person> person = Optional.ofNullable(personService.getPersonUserName(username));

        if (person.isEmpty()) {
            throw new BadCredentialsException("Unregistered User!");
        } else {
            if (passwordEncoder.matches(pwd, person.get().getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(person.get().getRole()));
            } else {
                throw new BadCredentialsException("Invalid password!");
            }
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    /**
     * In summary, this utility method is used to convert a role string into a list of GrantedAuthority objects,
     * which is a common format for representing user roles in Spring Security.
     * @param role
     * @return
     */

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return grantedAuthorities;
    }
}
