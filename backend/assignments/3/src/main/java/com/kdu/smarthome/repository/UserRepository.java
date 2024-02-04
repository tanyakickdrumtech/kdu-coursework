package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String userName);

    boolean existsByUsername(String username);

    boolean existsByEmailId(String emailId);
}
