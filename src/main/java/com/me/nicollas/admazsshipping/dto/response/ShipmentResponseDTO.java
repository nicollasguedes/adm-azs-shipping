package com.me.nicollas.admazsshipping.dto.response;

import com.me.nicollas.admazsshipping.entity.Shipment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ShipmentResponseDTO {

    private final UUID id;
    private final double price;
    private final double width;
    private final double height;
    private final double length;
    private final double weight;
    private List<ItemResponseDTO> itemList = new ArrayList<>();
    private List<ShipmentStatusResponseDTO> statusList = new ArrayList<>();
    private ConsigneeResponseDTO consignee;
    private final double TotalVolume;

    public ShipmentResponseDTO(Shipment shipment) {
        this.id = shipment.getId();
        this.price = shipment.getPrice();
        this.width = shipment.getWidth();
        this.height = shipment.getHeight();
        this.length = shipment.getLength();
        this.weight = shipment.getWeight();
        this.itemList = shipment.getItemList().stream().map(ItemResponseDTO::new).collect(Collectors.toList());
        this.statusList = shipment.getStatusList().stream().map(ShipmentStatusResponseDTO::new).collect(Collectors.toList());

        this.consignee = Optional.ofNullable(shipment.getConsignee())
                .map(ConsigneeResponseDTO::new)
                .orElse(null);

        if ( width != 0.0 && height != 0.0 && length != 0.0){
            this.TotalVolume = shipment.getTotalVolume();
        }else {
            this.TotalVolume = 0.0;
        }
    }
}
