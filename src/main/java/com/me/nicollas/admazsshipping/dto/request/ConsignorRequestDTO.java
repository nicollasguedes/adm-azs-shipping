package com.me.nicollas.admazsshipping.dto.request;


import com.me.nicollas.admazsshipping.enums.IdNumberTypeEnum;
import com.me.nicollas.admazsshipping.validatior.annotation.UniqueConsignorEmail;
import com.me.nicollas.admazsshipping.validatior.annotation.UniqueConsignorIdNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsignorRequestDTO {
    @NotBlank(message = "Consignor name can't be empty")
    private String name;

    @Email(message = "must be a well-formed email address: ${validatedValue}")
    @UniqueConsignorEmail(message = "Consignor e-mail already exists: ${validatedValue}")
    @NotBlank(message = "Consignor e-mail can't be empty")
    private String email;

    @NotNull(message = "The type of the identification number can't be null")
    private IdNumberTypeEnum identificationNumberType;

    @UniqueConsignorIdNumber(message = "Consignor identification number already exists: ${validatedValue}")
    @NotBlank(message = "Identification number can't be empty")
    private String identificationNumber;

    @NotBlank(message = "Consignor phone can't be empty")
    private String phone;

    @NotNull
    private AddressRequestDTO address;
}
