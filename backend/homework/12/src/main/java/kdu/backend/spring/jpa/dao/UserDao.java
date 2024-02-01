package kdu.backend.spring.jpa.dao;

import kdu.backend.spring.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDao extends JpaRepository<User, UUID>{
    @Query(value = "UPDATE users SET username = :username, logged_in = :loggedIn, time_zone = :timeZone WHERE id = :userId", nativeQuery = true)
    void updateUserDetails(@Param("userId") UUID userId, @Param("username") String username, @Param("loggedIn") int loggedIn, @Param("timeZone") String timeZone);
}
