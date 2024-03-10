package com.me.nicollas.admazsshipping.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequestDTO {

    @NotBlank(message = "Zip Code can't be empty")
    private String zipCode;

    @NotBlank(message = "Street Address can't be empty")
    private String streetAddress;

    @NotBlank(message = "Street Number can't be empty")
    private String streetNumber;

    @NotBlank(message = "Contry field can't be empty")
    private String country;

    @NotBlank(message = "City field can't be empty")
    private String city;

    @NotBlank(message = "State Field can't be empty")
    private String state;

    private String unitNumber;

    private String landmark;

    private String latitude;

    private String longitude;
}
