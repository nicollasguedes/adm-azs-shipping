package com.me.nicollas.admazsshipping.dto.request;

import com.me.nicollas.admazsshipping.enums.ShipmentStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentStatusRequestDTO {

    @NotNull(message = "Shipment Status can't be null")
    private ShipmentStatusEnum status;

    @NotBlank(message = "Shipment Status message can't be empty")
    private String message;
}
