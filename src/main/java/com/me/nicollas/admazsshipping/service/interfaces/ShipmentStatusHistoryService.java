package com.me.nicollas.admazsshipping.service.interfaces;

import com.me.nicollas.admazsshipping.dto.request.ShipmentStatusRequestDTO;
import com.me.nicollas.admazsshipping.entity.ShipmentStatusHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ShipmentStatusHistoryService {

    ShipmentStatusHistory saveShipmentStatusHistory(ShipmentStatusRequestDTO shipmentStatusRequestDTO, UUID shipmentId);

    ShipmentStatusHistory findShipmentStatusHistoryById(UUID shipmentStatusId);

    List<ShipmentStatusHistory> findAllShipmentStatusHistoryByShipmentId(UUID shipmentId);

    List<ShipmentStatusHistory> findAllShipmentStatusHistory();

    Page<ShipmentStatusHistory> pageAllShipmentStatusHistoryByShipmentId(UUID shipmentId, Pageable pageable);

    Page<ShipmentStatusHistory> pageAllShipmentStatusHistory(Pageable pageable);

    ShipmentStatusHistory updateShipmentStatusHistory(UUID shipmentStatusId, ShipmentStatusRequestDTO shipmentStatusRequestDTO);

    ShipmentStatusHistory deleteShipmentStatusHistory(UUID shipmentStatusId);

}
