package kdu.backend.spring.jdbc.dto;

import kdu.backend.spring.jdbc.model.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AllDataDto {
    private Tenant tenant;
    private Shift shift;
    private User user;
    private ShiftType shiftType;
    private UserShift shiftUser;
}
