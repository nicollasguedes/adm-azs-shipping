package com.me.nicollas.admazsshipping.dto.response;

import com.me.nicollas.admazsshipping.entity.Shipment;
import com.me.nicollas.admazsshipping.entity.ShipmentStatusHistory;
import com.me.nicollas.admazsshipping.enums.ShipmentStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Data
public class ShipmentStatusResponseDTO {

    private final UUID id;
    private final ShipmentStatusEnum status;
    private final String message;
    private final LocalDateTime createdAt;

    private final UUID shipmentId;

    public ShipmentStatusResponseDTO(ShipmentStatusHistory shipmentStatusHistory) {
        this.id = shipmentStatusHistory.getId();
        this.status = shipmentStatusHistory.getStatus();
        this.message = shipmentStatusHistory.getMessage();
        this.createdAt = shipmentStatusHistory.getCreatedAt();
        this.shipmentId = Optional.ofNullable(shipmentStatusHistory.getShipment())
                .map(Shipment::getId)
                .orElse(null);
    }
}
