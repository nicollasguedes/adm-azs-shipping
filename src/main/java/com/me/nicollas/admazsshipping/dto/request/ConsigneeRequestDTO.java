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
    private String name;

    @NotBlank(message = "Consignee phone can't be empty")
    private String phone;

    @NotBlank(message = "Consignee e-mail can't be empty")
    private String email;

    @NotNull(message = "Consignee address can't be null")
    private AddressRequestDTO address;
}
