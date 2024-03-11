package com.me.nicollas.admazsshipping.controller;

import com.me.nicollas.admazsshipping.dto.request.AddressRequestDTO;
import com.me.nicollas.admazsshipping.dto.response.AddressResponseDTO;
import com.me.nicollas.admazsshipping.entity.Address;
import com.me.nicollas.admazsshipping.service.impl.AddressServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressServiceImpl addressService;

    @Autowired
    public AddressController(AddressServiceImpl addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        Address savedAddress = addressService.saveAddress(addressRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new AddressResponseDTO(savedAddress));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> findAddressById(@Valid @PathVariable UUID addressId) {
        Address foundAddress = addressService.findAddressById(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(new AddressResponseDTO(foundAddress));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<AddressResponseDTO>> pageAllAddresss(@RequestHeader(defaultValue = "0") int page,
                                                         @RequestHeader(defaultValue = "10") int size,
                                                         @RequestHeader(defaultValue = "country") String sort,
                                                         @RequestHeader(defaultValue = "asc") String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<Address> addresss = addressService.pageAllAddress(pageable);
        Page<AddressResponseDTO> AddressResponseDTOPage = addresss.map(AddressResponseDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(AddressResponseDTOPage);
    }

    @PatchMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable UUID addressId,
                                                            @Valid @RequestBody AddressRequestDTO addressRequestDTO) {
        Address savedAddress = addressService.updateAddress(addressId, addressRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new AddressResponseDTO(savedAddress));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> deleteAddress(@PathVariable UUID addressId) {
        Address savedAddress = addressService.deleteAddress(addressId);
        return ResponseEntity.status(HttpStatus.OK).body(new AddressResponseDTO(savedAddress));
    }
}
