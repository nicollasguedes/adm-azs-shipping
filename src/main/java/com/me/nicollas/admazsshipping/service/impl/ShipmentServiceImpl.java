package com.me.nicollas.admazsshipping.service.impl;

import com.me.nicollas.admazsshipping.dto.request.ShipmentRequestDTO;
import com.me.nicollas.admazsshipping.dto.request.ShipmentRequestUpdateDTO;
import com.me.nicollas.admazsshipping.dto.request.ShipmentStatusRequestDTO;
import com.me.nicollas.admazsshipping.entity.*;
import com.me.nicollas.admazsshipping.enums.ShipmentStatusEnum;
import com.me.nicollas.admazsshipping.repository.ShipmentRepository;
import com.me.nicollas.admazsshipping.service.interfaces.ShipmentService;
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
public class ShipmentServiceImpl implements ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final ConsignorServiceImpl consignorService;
    private final ItemServiceImpl itemService;

    @Override
    public Shipment saveShipment(ShipmentRequestDTO shipmentRequestDTO, UUID consignorId) {
        Consignor consignor = consignorService.findConsignorById(consignorId);

        Shipment shipment = new Shipment(shipmentRequestDTO);
        shipment.setConsignor(consignor);

        ShipmentStatusHistory createdStatus = new ShipmentStatusHistory(new ShipmentStatusRequestDTO(
                ShipmentStatusEnum.LABEL_CREATED, "Label Created"));
        createdStatus.setShipment(shipment);
        shipment.getStatusList().add(createdStatus);

        return shipmentRepository.save(shipment);
    }

    @Override
    public Shipment findShipmentById(UUID shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Shipment not found with id: " + shipmentId));
    }

    @Override
    public List<Shipment> findAllShipmentByConsignorId(UUID consignorId) {
        return shipmentRepository.findAllByConsignorId(consignorId);
    }

    @Override
    public List<Shipment> findAllShipment() {
        return shipmentRepository.findAll();
    }

    @Override
    public Page<Shipment> pageAllShipmentByConsignorId(UUID consignorId, Pageable pageable) {
        return shipmentRepository.findByConsignorId(consignorId, pageable);
    }

    @Override
    public Page<Shipment> pageAllShipment(Pageable pageable) {
        return shipmentRepository.findAll(pageable);
    }

    @Override
    public Shipment updateShipment(UUID shipmentId, ShipmentRequestUpdateDTO shipmentRequestDTO) {
        Shipment shipment = findShipmentById(shipmentId);

        // Update basic shipment details
        shipment.setPrice(shipmentRequestDTO.getPrice());
        shipment.setWidth(shipmentRequestDTO.getWidth());
        shipment.setHeight(shipmentRequestDTO.getHeight());
        shipment.setLength(shipmentRequestDTO.getLength());
        shipment.setWeight(shipmentRequestDTO.getWeight());

        shipment.setConsignee(new Consignee(shipmentRequestDTO.getConsigneeRequest()));

        List<Item> updatedItems = shipmentRequestDTO.getItemList().stream()
                .map(itemUpdateRequestDTO -> {
                    if (itemUpdateRequestDTO.getId() != null) {
                        // Find the existing item in the itemList
                        Item existingItem = shipment.getItemList().stream()
                                .filter(item -> item.getId().equals(itemUpdateRequestDTO.getId()))
                                .findFirst()
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                        "Item not found with id: " + itemUpdateRequestDTO.getId()));

                        // Update the existing item
                        itemService.updateItem(existingItem.getId(), itemUpdateRequestDTO);
                        return existingItem;
                    } else {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Item Id cannot be Null");
                    }
                })
                .toList();

        // Update the shipment's item list
        shipment.getItemList().clear();
        shipment.getItemList().addAll(updatedItems);

        try {
            return shipmentRepository.save(shipment);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }






    @Override
    public Shipment deleteShipment(UUID shipmentId) {
        Shipment shipment = findShipmentById(shipmentId);
        try {
            shipmentRepository.deleteById(shipmentId);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error while deleting Shipment");
        }
        return shipment;
    }
}
