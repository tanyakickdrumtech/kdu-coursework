package kdu.backend.spring.jpa.controller;

import kdu.backend.spring.jpa.entity.ShiftUser;
import kdu.backend.spring.jpa.exception.MyCustomException;
import kdu.backend.spring.jpa.service.ShiftUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ShiftUserController {
    private final ShiftUserService shiftUserService;

    @Autowired
    public ShiftUserController(ShiftUserService shiftUserService) {
        this.shiftUserService = shiftUserService;
    }

    /**
     * saving the shift user
     * @param shiftUser
     * @return
     */
    @PostMapping("/shift")
    public ResponseEntity<String> saveShiftUser(@RequestBody ShiftUser shiftUser) {
        shiftUserService.saveShiftUser(shiftUser);
        return new ResponseEntity<>("ShiftUser saved successfully", HttpStatus.CREATED);
    }

    /**
     * getting shift from shift user id
     * @param shiftUserId
     * @return
     */
    @GetMapping("/shifts/{shiftUserId}")
    public ResponseEntity<ShiftUser> getShiftUserById(@PathVariable UUID shiftUserId) {
        ShiftUser retrievedShiftUser = shiftUserService.getShiftUserById(shiftUserId);
        if (retrievedShiftUser != null) {
            return new ResponseEntity<>(retrievedShiftUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * getting all the shift
     * @return
     */
    @GetMapping("/shifts/getAll")
    public ResponseEntity<List<ShiftUser>> getAllShiftUsers() {
        List<ShiftUser> allShiftUsers = shiftUserService.getAllShiftUsers();
        return new ResponseEntity<>(allShiftUsers, HttpStatus.OK);
    }

    /**
     * deleting the shift using the shift id
     * @param shiftUserId
     * @return
     * @throws MyCustomException
     */
    @DeleteMapping("/shift/delete/{shiftUserId}")
    public ResponseEntity<String> deleteShiftUser(@PathVariable UUID shiftUserId) throws MyCustomException {
        shiftUserService.deleteShiftUser(shiftUserId);
        return new ResponseEntity<>("ShiftUser deleted successfully", HttpStatus.OK);
    }
}
