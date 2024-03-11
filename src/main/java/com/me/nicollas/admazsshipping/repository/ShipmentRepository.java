package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.Shipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {


    List<Shipment> findAllByConsignorId(UUID consignorId);

    Page<Shipment> findByConsignorId(UUID consignorId, Pageable pageable);
}
