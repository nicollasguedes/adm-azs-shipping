package com.me.nicollas.admazsshipping.controller;

import com.me.nicollas.admazsshipping.dto.request.ItemRequestDTO;
import com.me.nicollas.admazsshipping.dto.response.ItemResponseDTO;
import com.me.nicollas.admazsshipping.entity.Item;
import com.me.nicollas.admazsshipping.service.impl.ItemServiceImpl;
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
@RequestMapping("/item")
public class ItemController {

    private final ItemServiceImpl itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/{shipmentId}")
    public ResponseEntity<ItemResponseDTO> createItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO, @PathVariable UUID shipmentId) {
        Item savedItem = itemService.saveItem(itemRequestDTO, shipmentId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ItemResponseDTO(savedItem));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemResponseDTO> findItemById(@Valid @PathVariable UUID itemId) {
        Item foundItem = itemService.findItemById(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(new ItemResponseDTO(foundItem));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ItemResponseDTO>> pageAllItems(@RequestHeader(defaultValue = "0") int page,
                                                   @RequestHeader(defaultValue = "10") int size,
                                                   @RequestHeader(defaultValue = "name") String sort,
                                                   @RequestHeader(defaultValue = "asc") String direction) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<Item> items = itemService.pageAllItem(pageable);
        Page<ItemResponseDTO> ItemResponseDTOPage = items.map(ItemResponseDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(ItemResponseDTOPage);
    }

    @GetMapping("/page/{shipmentId}")
    public ResponseEntity<Page<ItemResponseDTO>> pageAllItemByShipmentId(@RequestHeader(defaultValue = "0") int page,
                                                              @RequestHeader(defaultValue = "10") int size,
                                                              @RequestHeader(defaultValue = "name") String sort,
                                                              @RequestHeader(defaultValue = "asc") String direction,
                                                              @PathVariable UUID shipmentId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(direction), sort));
        Page<Item> items = itemService.pageAllItemByShipmentId(shipmentId, pageable);
        Page<ItemResponseDTO> ItemResponseDTOPage = items.map(ItemResponseDTO::new);

        return ResponseEntity.status(HttpStatus.OK).body(ItemResponseDTOPage);
    }

    @PatchMapping("/{itemId}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable UUID itemId,
                                                      @Valid @RequestBody ItemRequestDTO itemRequestDTO) {
        Item savedItem = itemService.updateItem(itemId, itemRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ItemResponseDTO(savedItem));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ItemResponseDTO> deleteItem(@PathVariable UUID itemId) {
        Item savedItem = itemService.deleteItem(itemId);
        return ResponseEntity.status(HttpStatus.OK).body(new ItemResponseDTO(savedItem));
    }
}
