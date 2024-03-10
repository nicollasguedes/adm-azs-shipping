package com.me.nicollas.admazsshipping.service.impl;

import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestDTO;
import com.me.nicollas.admazsshipping.entity.Consignor;
import com.me.nicollas.admazsshipping.repository.ConsignorRepository;
import com.me.nicollas.admazsshipping.service.interfaces.ConsignorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsignorServiceImpl implements ConsignorService {

    private final ConsignorRepository consignorRepository;

    @Override
    public Consignor saveConsignor(ConsignorRequestDTO consignorRequestDTO) {
        return null;
    }

    @Override
    public Consignor findConsignorById(String consignorId) {
        return null;
    }

    @Override
    public List<Consignor> listAllConsignor() {
        return null;
    }

    @Override
    public Page<Consignor> listAllConsignorPage(Pageable pageable) {
        return null;
    }

    @Override
    public Consignor updateConsignor(String consignorId, ConsignorRequestDTO consignorRequestDTO) {
        return null;
    }

    @Override
    public Consignor switchConsignorActivity(String consignorId) {
        return null;
    }

    @Override
    public Consignor deleteConsignor(String consignorId) {
        return null;
    }
}
