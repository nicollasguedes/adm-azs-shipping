package com.me.nicollas.admazsshipping.service.interfaces;

import com.me.nicollas.admazsshipping.dto.request.AddressRequestDTO;
import com.me.nicollas.admazsshipping.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AddressService {

    Address saveAddress(AddressRequestDTO addressRequestDTO);

    Address findAddressById(UUID addressId);

    List<Address> findAllAddress();

    Page<Address> pageAllAddress(Pageable pageable);

    Address updateAddress(UUID addressId, AddressRequestDTO addressRequestDTO);

    Address deleteAddress(UUID addressId);

}
