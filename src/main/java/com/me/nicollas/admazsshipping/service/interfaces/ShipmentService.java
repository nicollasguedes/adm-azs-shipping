package com.me.nicollas.admazsshipping.service.interfaces;

import com.me.nicollas.admazsshipping.dto.request.ShipmentRequestDTO;
import com.me.nicollas.admazsshipping.dto.request.ShipmentRequestUpdateDTO;
import com.me.nicollas.admazsshipping.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ShipmentService {

    Shipment saveShipment(ShipmentRequestDTO shipmentRequestDTO, UUID consignorId);

    Shipment findShipmentById(UUID shipmentId);

    List<Shipment> findAllShipmentByConsignorId(UUID consignorId);

    List<Shipment> findAllShipment();

    Page<Shipment> pageAllShipmentByConsignorId(UUID consignorId, Pageable pageable);

    Page<Shipment> pageAllShipment(Pageable pageable);

    Shipment updateShipment(UUID shipmentId, ShipmentRequestUpdateDTO shipmentRequestDTO);

    Shipment deleteShipment(UUID shipmentId);

}
