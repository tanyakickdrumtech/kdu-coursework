package com.example.springassessment2.repository;

import com.example.springassessment2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByEmail(String email);

    User findByUsername(String username);
}

