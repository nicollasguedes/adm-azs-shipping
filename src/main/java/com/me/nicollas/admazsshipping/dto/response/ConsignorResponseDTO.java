package com.me.nicollas.admazsshipping.dto.response;

import com.me.nicollas.admazsshipping.entity.Consignor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class ConsignorResponseDTO {
    private final UUID id;
    private final String name;
    private final String email;
    private final String phone;
    private final AddressResponseDTO address;
    private List<ShipmentResponseDTO> shipments = new ArrayList<>();

    public ConsignorResponseDTO(Consignor consignor) {
        this.id = consignor.getId();
        this.name = consignor.getName();
        this.phone = consignor.getPhone();
        this.email = consignor.getEmail();

        this.address = Optional.ofNullable(consignor.getAddress())
                .map(AddressResponseDTO::new)
                .orElse(null);

    }
}
