package com.me.nicollas.admazsshipping.service.impl;

import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestDTO;
import com.me.nicollas.admazsshipping.dto.request.ConsignorRequestUpdateDTO;
import com.me.nicollas.admazsshipping.entity.Consignor;
import com.me.nicollas.admazsshipping.repository.ConsignorRepository;
import com.me.nicollas.admazsshipping.service.interfaces.ConsignorService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsignorServiceImpl implements ConsignorService {

    private final ConsignorRepository consignorRepository;
    private final AddressServiceImpl addressService;

    @Override
    public Consignor saveConsignor(ConsignorRequestDTO consignorRequestDTO) {
        return consignorRepository.save(new Consignor(consignorRequestDTO));
    }

    @Override
    public Consignor findConsignorById(UUID consignorId) {
        return consignorRepository.findById(consignorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Consignor not found with id: " + consignorId));
    }

    @Override
    public List<Consignor> findAllConsignor() {
        return consignorRepository.findAll();
    }

    @Override
    public Page<Consignor> pageAllConsignor(Pageable pageable) {
        return consignorRepository.findAll(pageable);
    }

    @Override
    public Consignor updateConsignor(UUID consignorId, ConsignorRequestUpdateDTO consignorRequestDTO) {
        Consignor consignor = findConsignorById(consignorId);

        consignorRepository.findByIdNotAndEmail(consignorId, consignorRequestDTO.getEmail())
                .ifPresent(existingConsignor -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            "email: '" + consignorRequestDTO.getEmail() + "'. already exists.");
                });

        consignorRepository.findByIdNotAndIdentificationNumber(consignorId, consignorRequestDTO.getIdentificationNumber())
                .ifPresent(existingConsignor -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,
                            "identificationNumber: '" + consignorRequestDTO.getIdentificationNumber() + "'. already exists.");
                });

        consignor.setName(consignorRequestDTO.getName());
        consignor.setEmail(consignorRequestDTO.getEmail());
        consignor.setIdentificationNumberType(consignorRequestDTO.getIdentificationNumberType());
        consignor.setPhone(consignorRequestDTO.getPhone());

        consignor.setAddress(addressService.updateAddress(consignor.getAddress().getId(),
                consignorRequestDTO.getAddress()));

        return consignorRepository.save(consignor);
    }


    @Override
    public Consignor switchConsignorActivity(UUID consignorId) {
        Consignor consignor = findConsignorById(consignorId);
        consignor.setActive(!consignor.isActive());
        return consignorRepository.save(consignor);
    }

    @Override
    public Consignor deleteConsignor(UUID consignorId) {
        Consignor consignor = findConsignorById(consignorId);
        try {
            consignorRepository.deleteById(consignorId);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error while deleting Consignor");
        }
        return consignor;
    }
}
