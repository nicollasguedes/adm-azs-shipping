package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.Consignor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConsignorRepository extends JpaRepository<Consignor, UUID> {

    boolean existsByEmail(String email);

    boolean existsByIdentificationNumber(String identificationNumber);

    Optional<Consignor> findByIdNotAndEmail(UUID id, String email);

    Optional<Consignor> findByIdNotAndIdentificationNumber(UUID id, String email);

}
