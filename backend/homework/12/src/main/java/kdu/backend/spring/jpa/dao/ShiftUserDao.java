package kdu.backend.spring.jpa.dao;

import kdu.backend.spring.jpa.entity.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftUserDao extends JpaRepository<ShiftUser,UUID> {
    List<ShiftUser> findByShiftEndTimeAndId(Time endTime, UUID shiftUserId);
}
