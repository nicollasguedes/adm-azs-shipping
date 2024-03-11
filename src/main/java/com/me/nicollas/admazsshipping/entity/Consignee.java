package com.me.nicollas.admazsshipping.entity;

import com.me.nicollas.admazsshipping.dto.request.ConsigneeRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Consignee {

    public Consignee(ConsigneeRequestDTO consigneeRequestDTO) {
        this.name = consigneeRequestDTO.getName();
        this.phone = consigneeRequestDTO.getPhone();
        this.email = consigneeRequestDTO.getEmail();
        this.address = Optional.ofNullable(consigneeRequestDTO.getAddress())
                .map(Address::new)
                .orElse(null);
    }

    @Column(nullable = false, name = "consignee_name")
    private String name;

    @Column(nullable = false, name = "consignee_phone")
    private String phone;

    @Column(nullable = false, name = "consignee_email")
    private String email;

    @OneToOne
    @JoinColumn(name = "consignee_address_id")
    private Address address;
}
