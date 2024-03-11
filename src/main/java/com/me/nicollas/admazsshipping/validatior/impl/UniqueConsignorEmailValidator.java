package com.me.nicollas.admazsshipping.validatior.impl;

import com.me.nicollas.admazsshipping.repository.ConsignorRepository;
import com.me.nicollas.admazsshipping.validatior.annotation.UniqueConsignorEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueConsignorEmailValidator implements ConstraintValidator<UniqueConsignorEmail, String> {

    private final ConsignorRepository consignorRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        try {
            return email != null && !consignorRepository.existsByEmail(email);
        } catch (Exception ex) {
            throw new ValidationException("Error validating email uniqueness", ex);
        }
    }
}
