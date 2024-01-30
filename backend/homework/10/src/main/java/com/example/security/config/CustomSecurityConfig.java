package com.example.security.config;

import com.example.security.filters.TokenGeneratorFilter;
import com.example.security.filters.TokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
/**
 * this is basically configuring custom filters,
 * sets up specific authorization rules for different endpoints,
 * and disables CSRF protection in a Spring Security application.
 */
public class CustomSecurityConfig {
    @Bean
    SecurityFilterChain customDefaultFilter(HttpSecurity http) throws Exception{
        http
                .addFilterAfter(new TokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new TokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/person/login").permitAll()
                .requestMatchers("/users","/user/{userName}").hasAnyRole("BASIC","ADMIN")
                .requestMatchers("/user/admin").hasRole("ADMIN")
               .anyRequest().authenticated()).csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
