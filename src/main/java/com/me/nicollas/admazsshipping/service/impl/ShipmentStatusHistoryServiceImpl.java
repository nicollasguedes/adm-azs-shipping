package com.me.nicollas.admazsshipping.service.impl;

import com.me.nicollas.admazsshipping.dto.request.ShipmentStatusRequestDTO;
import com.me.nicollas.admazsshipping.entity.Shipment;
import com.me.nicollas.admazsshipping.entity.ShipmentStatusHistory;
import com.me.nicollas.admazsshipping.repository.ShipmentStatusHistoryRepository;
import com.me.nicollas.admazsshipping.service.interfaces.ShipmentStatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShipmentStatusHistoryServiceImpl implements ShipmentStatusHistoryService {

    private final ShipmentStatusHistoryRepository shipmentStatusRepository;
    private final ShipmentServiceImpl shipmentService;

    @Override
    public ShipmentStatusHistory saveShipmentStatusHistory(ShipmentStatusRequestDTO shipmentStatusRequestDTO, UUID shipmentId) {
        Shipment shipment = shipmentService.findShipmentById(shipmentId);

        ShipmentStatusHistory shipmentStatus = new ShipmentStatusHistory(shipmentStatusRequestDTO);
        shipmentStatus.setShipment(shipment);

        return shipmentStatusRepository.save(shipmentStatus);
    }

    @Override
    public ShipmentStatusHistory findShipmentStatusHistoryById(UUID shipmentStatusId) {
        return shipmentStatusRepository.findById(shipmentStatusId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Shipment Status not found with id: " + shipmentStatusId));
    }

    @Override
    public List<ShipmentStatusHistory> findAllShipmentStatusHistoryByShipmentId(UUID shipmentId) {
        return shipmentStatusRepository.findAllByShipmentId(shipmentId);
    }

    @Override
    public List<ShipmentStatusHistory> findAllShipmentStatusHistory() {
        return shipmentStatusRepository.findAll();
    }

    @Override
    public Page<ShipmentStatusHistory> pageAllShipmentStatusHistoryByShipmentId(UUID shipmentId, Pageable pageable) {
        return shipmentStatusRepository.findByShipmentId(shipmentId, pageable);
    }

    @Override
    public Page<ShipmentStatusHistory> pageAllShipmentStatusHistory(Pageable pageable) {
        return shipmentStatusRepository.findAll(pageable);
    }

    @Override
    public ShipmentStatusHistory updateShipmentStatusHistory(UUID shipmentStatusId, ShipmentStatusRequestDTO shipmentStatusRequestDTO) {
        ShipmentStatusHistory shipmentStatus = findShipmentStatusHistoryById(shipmentStatusId);

        shipmentStatus.setStatus(shipmentStatusRequestDTO.getStatus());
        shipmentStatus.setMessage(shipmentStatusRequestDTO.getMessage());

        return shipmentStatusRepository.save(shipmentStatus);
    }

    @Override
    public ShipmentStatusHistory deleteShipmentStatusHistory(UUID shipmentStatusId) {
        ShipmentStatusHistory shipmentStatus = findShipmentStatusHistoryById(shipmentStatusId);
        try {
            shipmentStatusRepository.deleteById(shipmentStatusId);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error while deleting Shipment Status");
        }
        return shipmentStatus;
    }
}
