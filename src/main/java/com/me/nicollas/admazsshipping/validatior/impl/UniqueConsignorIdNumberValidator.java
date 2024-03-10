package com.me.nicollas.admazsshipping.validatior.impl;

import com.me.nicollas.admazsshipping.repository.ConsignorRepository;
import com.me.nicollas.admazsshipping.validatior.annotation.UniqueConsignorIdNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueConsignorIdNumberValidator implements ConstraintValidator<UniqueConsignorIdNumber, String> {

    private final ConsignorRepository consignorRepository;

    @Override
    public boolean isValid(String idNumber, ConstraintValidatorContext context) {
        try {
            return idNumber == null || !consignorRepository.existsByIdentificationNumber(idNumber);
        } catch (Exception ex) {
            throw new ValidationException("Error validating id number uniqueness", ex);
        }
    }
}
