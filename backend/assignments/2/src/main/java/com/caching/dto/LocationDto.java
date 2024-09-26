package com.caching.dto;

import com.caching.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDto {
    private double latitude;
    private double longitude;

    /**
     * this method is used to convert the AddressDto to Address and return an obejct of type Address
     * @param locationDto
     * @return
     */
    public Location convertLocationDtoToLocation(LocationDto locationDto){
        return new Location(locationDto.getLatitude(),locationDto.getLongitude());
    }
}
