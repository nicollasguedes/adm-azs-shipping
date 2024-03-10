package com.me.nicollas.admazsshipping.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequestDTO {

    @NotNull(message = "Shipment price can't be null")
    private double price;

    private double width;

    private double height;

    private double length;

    @NotNull(message = "Shipment weight can't be null")
    private double weight;

    private List<ItemRequestDTO> itemRequestList = new ArrayList<>();

    private ConsigneeRequestDTO consigneeRequest;
}
