package com.me.nicollas.admazsshipping.validatior.annotation;

import com.me.nicollas.admazsshipping.validatior.impl.UniqueConsignorIdNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueConsignorIdNumberValidator.class)
@Documented
public @interface UniqueConsignorIdNumber {

    String message() default "Consignor identification number already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
