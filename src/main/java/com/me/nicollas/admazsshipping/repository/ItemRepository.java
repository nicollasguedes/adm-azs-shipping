package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemRepository extends JpaRepository<Item, UUID> {


}
