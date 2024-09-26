package com.caching.controller;

import com.caching.entities.Location;
import com.caching.exception.BadRequestException;
import com.caching.logging.LoggerDisplay;
import com.caching.service.GeoCodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@Scope("prototype")
public class GeoCodingController {
    private final GeoCodingService geoCodingService;
    @Autowired
    public GeoCodingController (GeoCodingService geoCodingService){
        this.geoCodingService=geoCodingService;
    }

    @GetMapping("/geocoding")
    public ResponseEntity<Location> getGeoCode(@RequestParam String address) throws BadRequestException {
        try {
            return ResponseEntity.ok(geoCodingService.getGeoCode(address));
        }catch (Exception e) {
            throw new BadRequestException("Invalid address");
        }
    }

    @GetMapping("/reverse-geocoding")
    public ResponseEntity<String> getReverseGeoCode(@RequestParam String latitude, @RequestParam String longitude) throws BadRequestException {
        try {
            return ResponseEntity.ok(geoCodingService.getReverseGeoCode(Double.parseDouble(latitude), Double.parseDouble(longitude)).getLabel());
        }catch (Exception e) {
            throw new BadRequestException("Invalid latitude/longitude");
        }
    }
}
