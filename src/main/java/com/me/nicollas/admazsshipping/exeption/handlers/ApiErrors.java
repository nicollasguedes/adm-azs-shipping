package com.me.nicollas.admazsshipping.exeption.handlers;

import lombok.Getter;

import java.util.List;

@Getter
public class ApiErrors extends RuntimeException {

    private final List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

}
