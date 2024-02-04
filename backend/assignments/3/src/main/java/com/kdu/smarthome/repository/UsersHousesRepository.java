package com.kdu.smarthome.repository;

import com.kdu.smarthome.entities.UsersHouses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersHousesRepository extends JpaRepository<UsersHouses,Long> {
}
