package com.caching.entities;

import com.caching.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private String label;

    /**
     * this method is used to convert the Address to AddressDto and return an object of type AddressDto
     * @param address
     * @return
     */
    public AddressDto convertAddressToAddressDto(Address address){
        return new AddressDto(address.getLabel());
    }
}
