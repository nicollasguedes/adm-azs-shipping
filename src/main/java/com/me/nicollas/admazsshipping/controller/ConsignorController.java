package com.me.nicollas.admazsshipping.controller;

import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestDTO;
import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestUpdateDTO;
import com.me.nicollas.admazsshipping.dto.response.ConsignorResponseDTO;
import com.me.nicollas.admazsshipping.entity.Consignor;
import com.me.nicollas.admazsshipping.service.impl.ConsignorServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{consignorId}")
    public ResponseEntity<ConsignorResponseDTO> findConsignorById(@Valid @PathVariable UUID consignorId) {
        Consignor foundConsignor = consignorService.findConsignorById(consignorId);
        return ResponseEntity.status(HttpStatus.OK).body(new ConsignorResponseDTO(foundConsignor));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ConsignorResponseDTO>> pageAllConsignors(@RequestHeader(defaultValue = "0") int page,
                                                             @RequestHeader(defaultValue = "10") int size,
                                                             @RequestHeader(defaultValue = "name") String sort,
                                                             @RequestHeader(defaultValue = "asc") String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<Consignor> consignors = consignorService.pageAllConsignor(pageable);
        Page<ConsignorResponseDTO> consignorResponseDTOPage = consignors.map(ConsignorResponseDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(consignorResponseDTOPage);
    }

    @PatchMapping("/{consignorId}")
    public ResponseEntity<ConsignorResponseDTO> updateConsignor(@PathVariable UUID consignorId,
                                                                @Valid @RequestBody ConsignorRequestUpdateDTO consignorRequestDTO) {
        Consignor savedConsignor = consignorService.updateConsignor(consignorId, consignorRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ConsignorResponseDTO(savedConsignor));
    }

    @DeleteMapping("/{consignorId}")
    public ResponseEntity<ConsignorResponseDTO> deleteConsignor(@PathVariable UUID consignorId) {
        Consignor savedConsignor = consignorService.deleteConsignor(consignorId);
        return ResponseEntity.status(HttpStatus.OK).body(new ConsignorResponseDTO(savedConsignor));
    }
}
