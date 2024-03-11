package com.me.nicollas.admazsshipping.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentRequestUpdateDTO {

    @NotNull(message = "Shipment price can't be null")
    private double price;

    private double width;

    private double height;

    private double length;

    @NotNull(message = "Shipment weight can't be null")
    private double weight;

    @NotEmpty(message = "Shipment item List can't be empty: ${validatedValue}")
    private List<ItemRequestUpdateDTO> itemList = new ArrayList<>();

    @NotNull(message = "Shipment consignee can't be null")
    private ConsigneeRequestDTO consigneeRequest;
}
