package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {


}
