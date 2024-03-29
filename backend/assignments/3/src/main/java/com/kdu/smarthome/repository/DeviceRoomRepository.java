package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.DeviceRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRoomRepository extends JpaRepository<DeviceRoom,Long> {
}
