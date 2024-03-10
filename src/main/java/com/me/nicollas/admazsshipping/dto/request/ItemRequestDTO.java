package com.me.nicollas.admazsshipping.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRequestDTO {


    @NotBlank(message = "Item name can't be empty")
    private String name;

    @NotNull(message = "Item value can't be null")
    private double value;

    @NotNull(message = "Item amount can't be null")
    private double amount;

    @NotBlank(message = "Item description can't be empty")
    private String description;
}
