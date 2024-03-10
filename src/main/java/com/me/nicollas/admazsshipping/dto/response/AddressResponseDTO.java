package com.me.nicollas.admazsshipping.dto.response;

import com.me.nicollas.admazsshipping.entity.Address;
import lombok.Data;

import java.util.UUID;

@Data
public class AddressResponseDTO {
    private final UUID id;
    private final String zipCode;
    private final String streetAddress;
    private final String streetNumber;
    private final String country;
    private final String city;
    private final String state;
    private final String unitNumber;
    private final String landmark;
    private final String latitude;
    private final String longitude;

    public AddressResponseDTO(Address address) {
        this.id = address.getId();
        this.zipCode = address.getZipCode();
        this.streetAddress = address.getStreetAddress();
        this.streetNumber = address.getStreetNumber();
        this.country = address.getCountry();
        this.city = address.getCity();
        this.state = address.getState();
        this.unitNumber = address.getUnitNumber();
        this.landmark = address.getLandmark();
        this.latitude = address.getLatitude();
        this.longitude = address.getLongitude();
    }
}
