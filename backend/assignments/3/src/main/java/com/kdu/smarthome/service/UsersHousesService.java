package com.kdu.smarthome.service;

import com.kdu.smarthome.entities.UsersHouses;
import com.kdu.smarthome.repository.UsersHousesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class providing business logic for operations related to UsersHouses entities.
 */
@Service
public class UsersHousesService {

    private UsersHousesRepository usersHousesRepository;

    /**
     * Constructs an instance of UsersHousesService.
     *
     * @param usersHousesRepository UsersHousesRepository for database interactions with UsersHouses entities
     */
    @Autowired
    public UsersHousesService(UsersHousesRepository usersHousesRepository) {
        this.usersHousesRepository = usersHousesRepository;
    }

    /**
     * Retrieves a list of all UsersHouses entities.
     *
     * @return List of all UsersHouses entities in the system
     */
    public List<UsersHouses> getAllUsersHouses() {
        return usersHousesRepository.findAll();
    }

    /**
     * Retrieves a UsersHouses entity by its ID.
     *
     * @param usersHousesId ID of the UsersHouses entity to retrieve
     * @return Optional containing the UsersHouses entity if found, empty otherwise
     */
    public Optional<UsersHouses> getUsersHousesById(Long usersHousesId) {
        return usersHousesRepository.findById(usersHousesId);
    }

    /**
     * Creates a new UsersHouses entity.
     *
     * @param usersHouses UsersHouses entity representing the new UsersHouses record
     * @return UsersHouses entity representing the created UsersHouses record
     */
    public UsersHouses createUsersHouses(UsersHouses usersHouses) {
        return usersHousesRepository.save(usersHouses);
    }

    /**
     * Deletes a UsersHouses entity by its ID.
     *
     * @param usersHousesId ID of the UsersHouses entity to delete
     */
    public void deleteUsersHouses(Long usersHousesId) {
        usersHousesRepository.deleteById(usersHousesId);
    }
}
