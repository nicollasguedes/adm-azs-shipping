package com.me.nicollas.admazsshipping.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsigneeRequestDTO {

    @NotBlank(message = "Consignee name can't be empty")
    private String consigneeName;

    @NotBlank(message = "Consignee phone can't be empty")
    private String consigneePhone;
    @NotBlank(message = "Consignee e-mail can't be empty")
    private String consigneeEmail;

    @NotNull(message = "Consignee address can't be empty")
    private AddressRequestDTO consigneeAddress;
}
