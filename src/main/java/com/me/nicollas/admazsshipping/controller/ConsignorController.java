package com.me.nicollas.admazsshipping.controller;

import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestDTO;
import com.me.nicollas.admazsshipping.dto.response.ConsignorResponseDTO;
import com.me.nicollas.admazsshipping.entity.Consignor;
import com.me.nicollas.admazsshipping.service.impl.ConsignorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consignor")
public class ConsignorController {

    private final ConsignorServiceImpl consignorService;

    @Autowired
    public ConsignorController(ConsignorServiceImpl consignorService) {
        this.consignorService = consignorService;
    }

    @PostMapping
    public ResponseEntity<ConsignorResponseDTO> createConsignor(@Valid @RequestBody ConsignorRequestDTO consignorRequestDTO) {
        Consignor savedConsignor = consignorService.saveConsignor(consignorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ConsignorResponseDTO(savedConsignor));
    }

    // Add other endpoints as needed for retrieving, updating, or deleting consignors

}
