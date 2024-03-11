package com.me.nicollas.admazsshipping.dto.response;

import com.me.nicollas.admazsshipping.entity.Consignee;
import lombok.Data;

import java.util.Optional;

@Data
public class ConsigneeResponseDTO {
    private final String name;
    private final String phone;
    private final String email;
    private final AddressResponseDTO address;
    public ConsigneeResponseDTO(Consignee consignee) {
        this.name = consignee.getName();
        this.phone = consignee.getPhone();
        this.email = consignee.getEmail();

        this.address = Optional.ofNullable(consignee.getAddress())
                .map(AddressResponseDTO::new)
                .orElse(null);
    }
}
