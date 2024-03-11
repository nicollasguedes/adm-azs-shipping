package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {

    List<Item> findAllByShipmentId(UUID shipment_id);

    Page<Item> findByShipmentId(UUID shipment_id, Pageable pageable);
}
