package kdu.backend.spring.jpa.dao;

import kdu.backend.spring.jpa.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Repository
public interface ShiftDao extends JpaRepository<Shift, UUID> {
    @Query("SELECT s FROM Shift s WHERE s.startDate = :startDate AND s.endDate = :endDate ORDER BY s.name ASC LIMIT 3")
    List<Shift> findTopThreeShiftsByDateRange(Date startDate, Date endDate);

}
