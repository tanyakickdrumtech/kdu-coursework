package kdu.backend.spring.jpa.controller;
import kdu.backend.spring.jpa.entity.ShiftType;
import kdu.backend.spring.jpa.service.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ShiftTypeController {
    private final ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    /**
     * creating the shift
     * @param shiftType
     * @return
     */
    @PostMapping("/shift")
    public ResponseEntity<String> saveShiftType(@RequestBody ShiftType shiftType) {
        shiftTypeService.saveShiftType(shiftType);
        return new ResponseEntity<>("ShiftType saved successfully", HttpStatus.CREATED);
    }

    /**
     * getting shift from shift type id
     * @param shiftTypeId
     * @return
     */
    @GetMapping("/shift/{shiftTypeId}")
    public ResponseEntity<ShiftType> getShiftTypeById(@PathVariable UUID shiftTypeId) {
        ShiftType retrievedShiftType = shiftTypeService.getShiftTypeById(shiftTypeId);
        if (retrievedShiftType != null) {
            return new ResponseEntity<>(retrievedShiftType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * getting all shifts
     * @return
     */

    @GetMapping("/shift/getAllShift")
    public ResponseEntity<List<ShiftType>> getAllShiftTypes() {
        List<ShiftType> allShiftTypes = shiftTypeService.getAllShiftTypes();
        return new ResponseEntity<>(allShiftTypes, HttpStatus.OK);
    }

}
