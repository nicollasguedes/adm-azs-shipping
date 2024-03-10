package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.Consignor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ConsignorRepository extends JpaRepository<Consignor, UUID> {

    boolean existsByEmail(String email);
}
