package com.kdu.smarthome.controller;

import com.kdu.smarthome.bean.AddUserToHouseRequestBean;
import com.kdu.smarthome.bean.HouseRegisterRequestBean;
import com.kdu.smarthome.bean.UpdateAddressRequestBean;
import com.kdu.smarthome.exception.HouseNotFoundException;
import com.kdu.smarthome.exception.UserNotAuthorizedException;
import com.kdu.smarthome.exception.UserNotFoundException;
import com.kdu.smarthome.response.HouseDetailsResponse;
import com.kdu.smarthome.response.HouseResponse;
import com.kdu.smarthome.response.HouseUserResponse;
import com.kdu.smarthome.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class handling house-related endpoints.
 */
@RestController
@RequestMapping("/api/v1/house")
public class HouseController {

    private final HouseService houseService;

    /**
     * Constructor for HouseController.
     *
     * @param houseService The house service.
     */
    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    /**
     * Endpoint to add a new house.
     *
     * @param houseRegisterRequestBean The details of the house to be added.
     * @return ResponseEntity with HouseResponse containing details of the added house.
     */
    @PostMapping
    public ResponseEntity<HouseResponse> addHouse(@RequestBody HouseRegisterRequestBean houseRegisterRequestBean) {
        return ResponseEntity.ok(houseService.addHouse(houseRegisterRequestBean));
    }

    /**
     * Endpoint to add a user to a house.
     *
     * @param houseId                  The identifier of the house.
     * @param addUserToHouseRequest    The details of the user to be added to the house.
     * @return ResponseEntity with HouseUserResponse containing details of the added user.
     * @throws UserNotFoundException      If the specified user is not found.
     * @throws HouseNotFoundException     If the specified house is not found.
     * @throws UserNotAuthorizedException If the user is not authorized to perform the operation.
     */
    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<HouseUserResponse> addUserToHouse(@PathVariable String houseId, @RequestBody AddUserToHouseRequestBean addUserToHouseRequest)
            throws UserNotFoundException, HouseNotFoundException, UserNotAuthorizedException {
        return ResponseEntity.ok(houseService.addUserToHouse(Long.parseLong(houseId), addUserToHouseRequest.getUsername()));
    }

    /**
     * Endpoint to retrieve a list of all houses.
     *
     * @return ResponseEntity with HouseResponse containing details of all houses.
     */
    @GetMapping
    public ResponseEntity<HouseResponse> getAllHouses() {
        return ResponseEntity.ok(houseService.getAllHouses());
    }

    /**
     * Endpoint to update the address of a house.
     *
     * @param houseId            The identifier of the house.
     * @param updateAddressRequest The new address to be set for the house.
     * @return ResponseEntity with HouseResponse containing details of the updated house.
     * @throws HouseNotFoundException If the specified house is not found.
     */
    @PutMapping
    public ResponseEntity<HouseResponse> updateHouseAddress(@RequestParam String houseId, @RequestBody UpdateAddressRequestBean updateAddressRequest)
            throws HouseNotFoundException {
        return ResponseEntity.ok(houseService.updateHouseAddress(Long.parseLong(houseId), updateAddressRequest.getAddress()));
    }

    /**
     * Endpoint to retrieve details of a specific house.
     *
     * @param houseId The identifier of the house.
     * @return ResponseEntity with HouseDetailsResponse containing details of the specified house.
     * @throws HouseNotFoundException If the specified house is not found.
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<HouseDetailsResponse> getHouseDetails(@PathVariable String houseId) throws HouseNotFoundException {
        return ResponseEntity.ok(houseService.getHouseDetails(Long.parseLong(houseId)));
    }
}
