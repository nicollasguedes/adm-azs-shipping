package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.ShipmentStatusHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ShipmentStatusHistoryRepository extends JpaRepository<ShipmentStatusHistory, UUID> {

    List<ShipmentStatusHistory> findAllByShipmentId(UUID shipment_id);

    Page<ShipmentStatusHistory> findByShipmentId(UUID shipment_id, Pageable pageable);
}
