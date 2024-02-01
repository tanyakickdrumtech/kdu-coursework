package kdu.backend.spring.jpa.dao;

import kdu.backend.spring.jpa.entity.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShiftTypeDao extends JpaRepository<ShiftType, UUID> {
}
