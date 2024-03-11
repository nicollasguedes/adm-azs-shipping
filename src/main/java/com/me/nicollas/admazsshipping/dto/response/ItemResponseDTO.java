package com.me.nicollas.admazsshipping.dto.response;

import com.me.nicollas.admazsshipping.entity.Item;
import com.me.nicollas.admazsshipping.entity.Shipment;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;

@Data
public class ItemResponseDTO {


    private final UUID id;
    private final String name;
    private final double value;
    private final double amount;
    private final String description;
    private final UUID shipmentId;

    public ItemResponseDTO(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.value = item.getValue();
        this.amount = item.getAmount();
        this.description = item.getDescription();
        this.shipmentId = Optional.ofNullable(item.getShipment())
                .map(Shipment::getId)
                .orElse(null);
    }
}
