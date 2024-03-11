package com.me.nicollas.admazsshipping.controller;

import com.me.nicollas.admazsshipping.dto.request.ShipmentRequestDTO;
import com.me.nicollas.admazsshipping.dto.request.ShipmentRequestUpdateDTO;
import com.me.nicollas.admazsshipping.dto.response.ShipmentResponseDTO;
import com.me.nicollas.admazsshipping.entity.Shipment;
import com.me.nicollas.admazsshipping.service.impl.ShipmentServiceImpl;
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
@RequestMapping("/shipment")
public class ShipmentController {

    private final ShipmentServiceImpl shipmentService;

    @Autowired
    public ShipmentController(ShipmentServiceImpl shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/{consignorId}")
    public ResponseEntity<ShipmentResponseDTO> createShipment(@Valid @RequestBody ShipmentRequestDTO shipmentRequestDTO, @PathVariable UUID consignorId) {
        Shipment savedShipment = shipmentService.saveShipment(shipmentRequestDTO, consignorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShipmentResponseDTO(savedShipment));
    }

    @GetMapping("/{shipmentId}")
    public ResponseEntity<ShipmentResponseDTO> findShipmentById(@Valid @PathVariable UUID shipmentId) {
        Shipment foundShipment = shipmentService.findShipmentById(shipmentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ShipmentResponseDTO(foundShipment));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ShipmentResponseDTO>> pageAllShipments(@RequestHeader(defaultValue = "0") int page,
                                                           @RequestHeader(defaultValue = "10") int size,
                                                           @RequestHeader(defaultValue = "price") String sort,
                                                           @RequestHeader(defaultValue = "asc") String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<Shipment> shipments = shipmentService.pageAllShipment(pageable);
        Page<ShipmentResponseDTO> ShipmentResponseDTOPage = shipments.map(ShipmentResponseDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(ShipmentResponseDTOPage);
    }

    @GetMapping("/page/{consignorId}")
    public ResponseEntity<Page<ShipmentResponseDTO>> pageAllShipmentByShipmentId(@RequestHeader(defaultValue = "0") int page,
                                                                      @RequestHeader(defaultValue = "10") int size,
                                                                      @RequestHeader(defaultValue = "price") String sort,
                                                                      @RequestHeader(defaultValue = "asc") String direction,
                                                                      @PathVariable UUID consignorId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<Shipment> shipments = shipmentService.pageAllShipmentByConsignorId(consignorId, pageable);
        Page<ShipmentResponseDTO> ShipmentResponseDTOPage = shipments.map(ShipmentResponseDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(ShipmentResponseDTOPage);
    }

    @PatchMapping("/{shipmentId}")
    public ResponseEntity<ShipmentResponseDTO> updateShipment(@PathVariable UUID shipmentId,
                                                              @Valid @RequestBody ShipmentRequestUpdateDTO shipmentRequestDTO) {
        Shipment savedShipment = shipmentService.updateShipment(shipmentId, shipmentRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ShipmentResponseDTO(savedShipment));
    }

    @DeleteMapping("/{shipmentId}")
    public ResponseEntity<ShipmentResponseDTO> deleteShipment(@PathVariable UUID shipmentId) {
        Shipment savedShipment = shipmentService.deleteShipment(shipmentId);
        return ResponseEntity.status(HttpStatus.OK).body(new ShipmentResponseDTO(savedShipment));
    }
}
