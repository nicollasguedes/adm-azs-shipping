package com.me.nicollas.admazsshipping.controller;

import com.me.nicollas.admazsshipping.dto.request.ShipmentStatusRequestDTO;
import com.me.nicollas.admazsshipping.dto.response.ShipmentStatusResponseDTO;
import com.me.nicollas.admazsshipping.entity.ShipmentStatusHistory;
import com.me.nicollas.admazsshipping.service.impl.ShipmentStatusHistoryServiceImpl;
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
@RequestMapping("/shipment-status")
public class ShipmentStatusHistoryController {

    private final ShipmentStatusHistoryServiceImpl ShipmentStatusService;

    @Autowired
    public ShipmentStatusHistoryController(ShipmentStatusHistoryServiceImpl ShipmentStatusService) {
        this.ShipmentStatusService = ShipmentStatusService;
    }

    @PostMapping("{shipmentId}")
    public ResponseEntity<ShipmentStatusResponseDTO> createShipmentStatusHistory(@Valid @RequestBody ShipmentStatusRequestDTO ShipmentStatusRequestDTO,
                                                                                 @PathVariable UUID shipmentId) {
        ShipmentStatusHistory savedShipmentStatusHistory = ShipmentStatusService.saveShipmentStatusHistory(ShipmentStatusRequestDTO, shipmentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShipmentStatusResponseDTO(savedShipmentStatusHistory));
    }

    @GetMapping("/{shipmentStatusId}")
    public ResponseEntity<ShipmentStatusResponseDTO> findShipmentStatusHistoryById(@Valid @PathVariable UUID shipmentStatusId) {
        ShipmentStatusHistory foundShipmentStatusHistory = ShipmentStatusService.findShipmentStatusHistoryById(shipmentStatusId);
        return ResponseEntity.status(HttpStatus.OK).body(new ShipmentStatusResponseDTO(foundShipmentStatusHistory));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ShipmentStatusResponseDTO>> pageAllShipmentStatusHistorys(@RequestHeader(defaultValue = "0") int page,
                                                                                         @RequestHeader(defaultValue = "10") int size,
                                                                                         @RequestHeader(defaultValue = "status") String sort,
                                                                                         @RequestHeader(defaultValue = "asc") String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<ShipmentStatusHistory> ShipmentStatusPage = ShipmentStatusService.pageAllShipmentStatusHistory(pageable);
        Page<ShipmentStatusResponseDTO> ShipmentStatusResponseDTOPage = ShipmentStatusPage.map(ShipmentStatusResponseDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(ShipmentStatusResponseDTOPage);
    }

    @GetMapping("/page/{shipmentId}")
    public ResponseEntity<Page<ShipmentStatusResponseDTO>> pageAllShipmentStatusHistoryByShipmentId(@RequestHeader(defaultValue = "0") int page,
                                                                                                    @RequestHeader(defaultValue = "10") int size,
                                                                                                    @RequestHeader(defaultValue = "status") String sort,
                                                                                                    @RequestHeader(defaultValue = "asc") String direction,
                                                                                                    @PathVariable UUID shipmentId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<ShipmentStatusHistory> ShipmentStatusPage = ShipmentStatusService.pageAllShipmentStatusHistoryByShipmentId(shipmentId, pageable);
        Page<ShipmentStatusResponseDTO> ShipmentStatusResponseDTOPage = ShipmentStatusPage.map(ShipmentStatusResponseDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(ShipmentStatusResponseDTOPage);
    }

    @PatchMapping("/{shipmentStatusId}")
    public ResponseEntity<ShipmentStatusResponseDTO> updateShipmentStatusHistory(@PathVariable UUID shipmentStatusId,
                                                                                 @Valid @RequestBody ShipmentStatusRequestDTO ShipmentStatusRequestDTO) {
        ShipmentStatusHistory savedShipmentStatusHistory = ShipmentStatusService.updateShipmentStatusHistory(shipmentStatusId, ShipmentStatusRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ShipmentStatusResponseDTO(savedShipmentStatusHistory));
    }

    @DeleteMapping("/{shipmentStatusId}")
    public ResponseEntity<ShipmentStatusResponseDTO> deleteShipmentStatusHistory(@PathVariable UUID shipmentStatusId) {
        ShipmentStatusHistory savedShipmentStatusHistory = ShipmentStatusService.deleteShipmentStatusHistory(shipmentStatusId);
        return ResponseEntity.status(HttpStatus.OK).body(new ShipmentStatusResponseDTO(savedShipmentStatusHistory));
    }
}
