package com.me.nicollas.admazsshipping.validatior.impl;

import com.me.nicollas.admazsshipping.repository.ConsignorRepository;
import com.me.nicollas.admazsshipping.validatior.annotation.UniqueConsignorEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueConsignorEmailValidator implements ConstraintValidator<UniqueConsignorEmail, String> {

    private final ConsignorRepository consignorRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && !consignorRepository.existsByEmail(email);
    }
}
