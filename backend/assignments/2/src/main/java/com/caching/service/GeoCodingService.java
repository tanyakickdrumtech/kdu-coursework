package com.caching.service;


import com.caching.dto.AddressDto;
import com.caching.dto.LocationDto;
import com.caching.entities.Address;
import com.caching.entities.Location;
import com.caching.exception.BadRequestException;
import com.caching.logging.LoggerDisplay;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeoCodingService {
    @Value("${geocoding-url}")
    private String geocodingUrl;

    @Value("${reverse-geocoding-url}")
    private String reverseGeocodingUrl;

    @Value("${api-key}")
    private String accessKey;

    private final RestTemplate restTemplate;

    public GeoCodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * This methode is used calling the fetch method to get the geo code address
     * and if any exeption occurs it handles it accordingly
     * @param address
     * @return
     * @throws BadRequestException
     */
    @Cacheable(value = "geocoding", key = "#address", unless = "#result == null or #address.equals('goa')")
    public Location getGeoCode(String address) throws BadRequestException {
        double lat;
        double lon;
        String result;
        try {
            result = fetchGeoCodeFromExternalAPI(address);
            LoggerDisplay.logInfo(result);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            JsonNode firstObject = jsonNode.path("data").get(0);

            lat = firstObject.path("latitude").asDouble();
            lon = firstObject.path("longitude").asDouble();

        } catch (Exception e) {
            LoggerDisplay.logError("Exception occured during geo-coding");
            throw new BadRequestException("address is not correct");
        }
        LocationDto locationDto = new LocationDto(lat, lon);
        LoggerDisplay.logInfo("GeoCoding done successfully");
        return locationDto.convertLocationDtoToLocation(locationDto);
    }

    /**
     * The methode is used to fetch the geo-code from the thrid party url using the spring built in rest template
     * @param address
     * @return
     */
    public String fetchGeoCodeFromExternalAPI(String address) {
        String apiUrl = geocodingUrl + "access_key=" + accessKey + "&query=" + address;
        return restTemplate.getForObject(apiUrl, String.class);
    }

    /**
     * The methode is used to fetch the reverse geo-code from the thrid party url using the spring built in rest template
     * @param latitude
     * @param longitude
     * @return
     */
    public String fetchReverseGeoCodeFromExternalAPI(double latitude, double longitude) {
        String apiUrl = reverseGeocodingUrl + "access_key=" + accessKey + "&query=" + latitude + "," + longitude;
        return restTemplate.getForObject(apiUrl, String.class);
    }

    /**
     * This methode is used calling the fetch method to get the reverse geo code address
     * and if any exeption occurs it handles it accordingly
     * @param latitude
     * @param longitude
     * @return
     * @throws BadRequestException
     */
    @Cacheable(value = "reverse-geocoding", key = "{#latitude, #longitude}", unless = "#result == null")
    public Address getReverseGeoCode(double latitude, double longitude) throws BadRequestException {
        String label;
        String result;
        try {
            result = fetchReverseGeoCodeFromExternalAPI(latitude, longitude);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(result);
            JsonNode firstObject = jsonNode.path("data").get(0);
            label = firstObject.path("label").asText();
        } catch (Exception e) {
            LoggerDisplay.logError("Exception occured during reverse-geo-coding");
            throw new BadRequestException("latitude/longitude is not correct");
        }
        AddressDto addressDto = new AddressDto(label);
        LoggerDisplay.logInfo("Reverse-GeoCoding done successfully");
        return addressDto.convertAddressDtoToAddress(addressDto);
    }
}
