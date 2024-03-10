package com.me.nicollas.admazsshipping.repository;

import com.me.nicollas.admazsshipping.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {


}
