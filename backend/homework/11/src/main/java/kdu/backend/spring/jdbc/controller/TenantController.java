package kdu.backend.spring.jdbc.controller;


import kdu.backend.spring.jdbc.dao.ShiftDao;
import kdu.backend.spring.jdbc.dto.AllDataDto;
import kdu.backend.spring.jdbc.exception.MyCustomException;
import kdu.backend.spring.jdbc.model.*;
import kdu.backend.spring.jdbc.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class TenantController {

    private final TenantService tenantService;
    private final ShiftService shiftService;
    private final UserService userService;
    private final ShiftTypeService shiftTypeService;
    private final UserShiftService userShiftService;

    @Autowired
    public TenantController(TenantService tenantService, ShiftService shiftService, UserService userService, ShiftTypeService shiftTypeService, UserShiftService userShiftService) {
        this.tenantService = tenantService;
        this.shiftService = shiftService;
        this.userService = userService;
        this.shiftTypeService = shiftTypeService;
        this.userShiftService = userShiftService;
    }

    /**
     * taking the tenant data and saving whole data in it
     * @param tenantData
     * @return
     */

    @PostMapping("/tenant_data")
    public ResponseEntity<String> saveTenantData(@RequestBody AllDataDto tenantData) {
        try {
            tenantService.saveTenantData(tenantData);
            return ResponseEntity.ok("Data saved Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error Occured while adding data");
        }
    }

    /**
     * saving the details in shift data
     * @param shift
     * @return
     * @throws MyCustomException
     */
    @PostMapping("/shifts")
    public ResponseEntity<String> saveShift(@RequestBody Shift shift) throws MyCustomException {
        shiftService.saveShift(shift);
        return ResponseEntity.ok("Shift saved");
    }

    /**
     * getting the shift using the tenant id
     * @param tenantId
     * @return
     * @throws MyCustomException
     */
    @GetMapping("/shifts/{tenantId}")
    public ResponseEntity<List<Shift>> getShift(@PathVariable UUID tenantId) throws MyCustomException {
        List<Shift> shift =  shiftService.getShifts(tenantId);
        //log.info("shift entered");
        return ResponseEntity.ok(shift);
    }

    /**
     * this rest api is basically saving the user in our database
     * @param user
     * @return
     * @throws MyCustomException
     */
    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) throws MyCustomException {
        userService.saveUser(user);
        return ResponseEntity.ok("User Saved Successfully");
    }

    /**
     * getting the user using the tenant id
     * @param tenantId
     * @return
     */
    @GetMapping("/usersByTenant/{tenantId}")
    public ResponseEntity<List<User>> getUser(@PathVariable UUID tenantId) {
        List<User> user = userService.getUsers(tenantId);
        return ResponseEntity.ok(user);
    }

    /**
     * this rest api is basically getting the user through the user id
     * @param userId
     * @return
     * @throws MyCustomException
     */
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserbyId(@PathVariable UUID userId) throws MyCustomException {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    /**
     * api to update the user
     * @param userId
     * @param user
     * @return
     * @throws MyCustomException
     */
    @PutMapping("/updateuser/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable UUID userId, @RequestBody User user) throws MyCustomException {
        userService.updateUser(userId, user);
        return ResponseEntity.ok("User updated Successfully");
    }

    /**
     * saving the shift type among the 3 provided
     * @param shiftType
     * @return
     */
    @PostMapping("/shift_type")
    public ResponseEntity<String> saveShiftType(@RequestBody ShiftType shiftType) {
        shiftTypeService.saveShiftType(shiftType);
        return ResponseEntity.ok("ShiftType saved successfully");
    }

    /**
     * getting the shifttype using the tenant id
     * @param tenantId
     * @return
     */
    @GetMapping("/shift_types/{tenantId}")
    public ResponseEntity<List<ShiftType>> getShiftType(@PathVariable UUID tenantId) {
        List<ShiftType> shiftType = (List<ShiftType>) shiftTypeService.getShiftTypes(tenantId);
        return ResponseEntity.ok(shiftType);
    }

    /**
     * saving the user shift
     * @param userShift
     * @return
     */
    @PostMapping("/shift_user")
    public ResponseEntity<String> saveUserShift(@RequestBody UserShift userShift) {
        userShiftService.saveUserShift(userShift);
        return ResponseEntity.ok("ShiftUser saved successfully");
    }

    /**
     * retrieving the user shift using tenant id
     * @param tenantId
     * @return
     */
    @GetMapping("shift_user/{tenantId}")
    public ResponseEntity<List<UserShift>> getUserShift(@PathVariable UUID tenantId) {
        List<UserShift> userShift = userShiftService.getUserShiftById(tenantId);
        return ResponseEntity.ok(userShift);
    }

    /**
     * getting all the tenants
     * @return
     */
    @GetMapping("/tenant")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        return ResponseEntity.ok(tenants);
    }
}