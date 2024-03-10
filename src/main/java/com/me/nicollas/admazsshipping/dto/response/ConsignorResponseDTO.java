package com.me.nicollas.admazsshipping.dto.response;

import com.me.nicollas.admazsshipping.entity.Consignor;
import lombok.Data;

import java.util.UUID;

@Data
public class ConsignorResponseDTO {
    private final UUID id;
    private final String name;
    private final String email;
    private final String phone;
    private final AddressResponseDTO address;

    public ConsignorResponseDTO(Consignor consignor) {
        this.id = consignor.getId();
        this.name = consignor.getName();
        this.phone = consignor.getPhone();
        this.email = consignor.getEmail();

        if (consignor.getAddress() == null) {
            this.address = null;
        } else {
            this.address = new AddressResponseDTO(consignor.getAddress());
        }
    }
}
