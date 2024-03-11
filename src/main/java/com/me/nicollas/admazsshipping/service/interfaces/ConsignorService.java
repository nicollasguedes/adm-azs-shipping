package com.me.nicollas.admazsshipping.service.interfaces;

import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestDTO;
import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestUpdateDTO;
import com.me.nicollas.admazsshipping.entity.Consignor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ConsignorService {

    Consignor saveConsignor(ConsignorRequestDTO consignorRequestDTO);

    Consignor findConsignorById(UUID consignorId);

    List<Consignor> findAllConsignor();

    Page<Consignor> pageAllConsignor(Pageable pageable);

    Consignor updateConsignor(UUID consignorId, ConsignorRequestUpdateDTO consignorRequestDTO);

    Consignor switchConsignorActivity(UUID consignorId);

    Consignor deleteConsignor(UUID consignorId);

}
