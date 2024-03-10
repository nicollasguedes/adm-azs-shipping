package com.me.nicollas.admazsshipping.exeption.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private final String error;
    private final List<String> errors;
}