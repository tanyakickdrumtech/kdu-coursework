package com.caching.entities;

import com.caching.dto.LocationDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Location {
    private double latitude;
    private double longitude;

    /**
     * this method is used to convert the Locatio to LocationDto and return an object of type LocationDto
     * @param location
     * @return
     */
    public LocationDto convertLocationToLocationDto(Location location){
        return new LocationDto(location.getLatitude(),location.getLongitude());
    }
}
