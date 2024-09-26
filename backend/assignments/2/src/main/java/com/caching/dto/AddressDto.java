package com.caching.dto;

import com.caching.entities.Address;
import com.caching.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDto {
    private String label;

    /**
     * this method is used to convert the AddressDto to Address and return an obejct of type Address
     * @param addressDto
     * @return
     */
    public Address convertAddressDtoToAddress(AddressDto addressDto){
        return new Address(addressDto.getLabel());
    }
}
