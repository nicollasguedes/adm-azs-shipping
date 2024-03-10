package com.me.nicollas.admazsshipping.validatior.annotation;

import com.me.nicollas.admazsshipping.validatior.impl.UniqueConsignorEmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueConsignorEmailValidator.class)
@Documented
public @interface UniqueConsignorEmail {

    String message() default "Consignor e-mail already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
