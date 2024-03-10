package com.me.nicollas.admazsshipping.service.interfaces;

import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestDTO;
import com.me.nicollas.admazsshipping.entity.Consignor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConsignorService {

    Consignor saveConsignor(ConsignorRequestDTO consignorRequestDTO);

    Consignor findConsignorById(String consignorId);

    List<Consignor> listAllConsignor();

    Page<Consignor> listAllConsignorPage(Pageable pageable);

    Consignor updateConsignor(String consignorId, ConsignorRequestDTO consignorRequestDTO);

    Consignor switchConsignorActivity(String consignorId);

    Consignor deleteConsignor(String consignorId);

}
