package com.me.nicollas.admazsshipping.service.impl;

import com.me.nicollas.admazsshipping.dto.request.AddressRequestDTO;
import com.me.nicollas.admazsshipping.entity.Address;
import com.me.nicollas.admazsshipping.repository.AddressRepository;
import com.me.nicollas.admazsshipping.service.interfaces.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address saveAddress(AddressRequestDTO addressRequestDTO) {
        return addressRepository.save(new Address(addressRequestDTO));
    }

    @Override
    public Address findAddressById(UUID addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Address not found with id: " + addressId));
    }

    @Override
    public List<Address> findAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Page<Address> pageAllAddress(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Override
    public Address updateAddress(UUID addressId, AddressRequestDTO addressRequestDTO) {
        Address address = findAddressById(addressId);

        address.setZipCode(addressRequestDTO.getZipCode());
        address.setStreetAddress(addressRequestDTO.getStreetAddress());
        address.setStreetNumber(addressRequestDTO.getStreetNumber());
        address.setCountry(addressRequestDTO.getCountry());
        address.setCity(addressRequestDTO.getCity());
        address.setState(addressRequestDTO.getState());
        address.setUnitNumber(addressRequestDTO.getState());
        address.setLandmark(addressRequestDTO.getLandmark());
        address.setLatitude(addressRequestDTO.getLatitude());
        address.setLongitude(addressRequestDTO.getLongitude());

        return addressRepository.save(address);
    }

    @Override
    public Address deleteAddress(UUID addressId) {
        Address address = findAddressById(addressId);
        try {
            addressRepository.deleteById(addressId);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error while deleting Address");
        }
        return address;
    }
}
