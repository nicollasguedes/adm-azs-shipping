package com.me.nicollas.admazsshipping.service.impl;

import com.me.nicollas.admazsshipping.dto.request.ItemRequestDTO;
import com.me.nicollas.admazsshipping.dto.request.ItemRequestUpdateDTO;
import com.me.nicollas.admazsshipping.entity.Item;
import com.me.nicollas.admazsshipping.entity.Shipment;
import com.me.nicollas.admazsshipping.repository.ItemRepository;
import com.me.nicollas.admazsshipping.repository.ShipmentRepository;
import com.me.nicollas.admazsshipping.service.interfaces.ItemService;
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
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ShipmentRepository shipmentRepository;

    @Override
    public Item saveItem(ItemRequestDTO itemRequestDTO, UUID shipmentId) {
        Shipment shipment = findShipmentById(shipmentId);

        Item item = new Item(itemRequestDTO);
        item.setShipment(shipment);

        return itemRepository.save(item);
    }

    @Override
    public Item findItemById(UUID itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Item not found with id: " + itemId));
    }

    @Override
    public List<Item> findAllItemByShipmentId(UUID shipmentId) {
        return itemRepository.findAllByShipmentId(shipmentId);
    }

    @Override
    public List<Item> findAllItem() {
        return itemRepository.findAll();
    }

    @Override
    public Page<Item> pageAllItemByShipmentId(UUID shipmentId, Pageable pageable) {
        return itemRepository.findByShipmentId(shipmentId, pageable);
    }

    @Override
    public Page<Item> pageAllItem(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    @Override
    public Item updateItem(UUID itemId, ItemRequestDTO itemRequestDTO) {
        Item item = findItemById(itemId);

        item.setName(itemRequestDTO.getName());
        item.setValue(itemRequestDTO.getValue());
        item.setAmount(itemRequestDTO.getAmount());
        item.setDescription(itemRequestDTO.getDescription());

        return itemRepository.save(item);
    }

    public Item updateItem(UUID itemId, ItemRequestUpdateDTO itemRequestDTO) {
        var item = findItemById(itemId);

        item.setName(itemRequestDTO.getName());
        item.setAmount(itemRequestDTO.getAmount());
        item.setValue(itemRequestDTO.getValue());
        item.setDescription(itemRequestDTO.getDescription());

        return itemRepository.save(item);
    }

    @Override
    public Item deleteItem(UUID itemId) {
        Item item = findItemById(itemId);
        try {
            itemRepository.deleteById(itemId);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Error while deleting Item");
        }
        return item;
    }

    public Shipment findShipmentById(UUID shipmentId) {
        return shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Shipment not found with id: " + shipmentId));
    }
}
