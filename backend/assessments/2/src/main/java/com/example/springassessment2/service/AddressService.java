package com.example.springassessment2.service;

import com.example.springassessment2.entity.Address;
import com.example.springassessment2.exception.custom.MyCustomException;
import com.example.springassessment2.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void saveAddress(Address address) {
        try {
            addressRepository.save(address);
        } catch (Exception e) {
            throw new MyCustomException("Failed to save address.");
        }
    }
}
