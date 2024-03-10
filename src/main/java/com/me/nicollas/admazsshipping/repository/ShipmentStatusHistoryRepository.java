package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.ShipmentStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShipmentStatusHistoryRepository extends JpaRepository<ShipmentStatusHistory, UUID> {


}
