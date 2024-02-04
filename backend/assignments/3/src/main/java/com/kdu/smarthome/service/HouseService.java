package com.kdu.smarthome.service;

import com.kdu.smarthome.bean.HouseRegisterRequestBean;
import com.kdu.smarthome.entities.House;
import com.kdu.smarthome.entities.User;
import com.kdu.smarthome.exception.HouseNotFoundException;
import com.kdu.smarthome.exception.UserNotAuthorizedException;
import com.kdu.smarthome.exception.UserNotFoundException;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.UserRepository;
import com.kdu.smarthome.response.HouseDetailsResponse;
import com.kdu.smarthome.response.HouseResponse;
import com.kdu.smarthome.response.HouseUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing operations related to Houses.
 */
@Service
public class HouseService {

    private static final String MESSAGE = "House not found with id: ";

    private HouseRepository houseRepository;
    private UserRepository userRepository;

    /**
     * Constructs an instance of HouseService.
     *
     * @param houseRepository  HouseRepository for database interactions with House entities
     * @param userRepository   UserRepository for database interactions with User entities
     */
    @Autowired
    public HouseService(HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    /**
     * Adds a new House to the system.
     *
     * @param reqHouse HouseRegisterRequestBean containing details of the new House
     * @return The added House along with a response message
     */
    public HouseResponse addHouse(HouseRegisterRequestBean reqHouse) {
        House house = new House();
        house.setHouse_name(reqHouse.getHouseName());
        house.setAddress(reqHouse.getAddress());
        House savedHouse = houseRepository.save(house);
        return new HouseResponse("House added successfully.", savedHouse);
    }

    /**
     * Adds a User to a specific House.
     *
     * @param houseId  ID of the House
     * @param username Username of the User to be added
     * @return The response message along with the added User's username
     * @throws HouseNotFoundException       if the House is not found
     * @throws UserNotFoundException        if the User is not found
     * @throws UserNotAuthorizedException    if the User is not authorized for the House
     */
    public HouseUserResponse addUserToHouse(Long houseId, String username)
            throws HouseNotFoundException, UserNotFoundException, UserNotAuthorizedException {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(MESSAGE + houseId));
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        if (!house.getUsers().contains(user.get())) {
            throw new UserNotAuthorizedException("User not authorized");
        }

        return new HouseUserResponse("User added to house successfully.", username);
    }

    /**
     * Retrieves a list of all Houses in the system.
     *
     * @return The response message along with the details of the first House in the list
     */
    public HouseResponse getAllHouses() {
        List<House> houses = houseRepository.findAll();
        return new HouseResponse("Houses retrieved successfully.", houses.get(0));
    }

    /**
     * Updates the address of a specific House.
     *
     * @param houseId    ID of the House
     * @param newAddress New address to be set for the House
     * @return The updated House along with a response message
     * @throws HouseNotFoundException if the House is not found
     */
    public HouseResponse updateHouseAddress(Long houseId, String newAddress) throws HouseNotFoundException {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(MESSAGE + houseId));

        house.setAddress(newAddress);
        House updatedHouse = houseRepository.save(house);

        return new HouseResponse("House address updated successfully.", updatedHouse);
    }

    /**
     * Retrieves the details of a specific House.
     *
     * @param houseId ID of the House
     * @return The response message along with the details of the House
     * @throws HouseNotFoundException if the House is not found
     */
    public HouseDetailsResponse getHouseDetails(Long houseId) throws HouseNotFoundException {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(MESSAGE + houseId));

        return new HouseDetailsResponse("House details retrieved successfully.", house);
    }
}
