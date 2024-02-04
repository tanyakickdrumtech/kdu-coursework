package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    Optional<Device> findByKickstonId(String kickstonId);
}
