package com.me.nicollas.admazsshipping.service.interfaces;

import com.me.nicollas.admazsshipping.dto.request.ItemRequestDTO;
import com.me.nicollas.admazsshipping.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ItemService {

    Item saveItem(ItemRequestDTO itemRequestDTO, UUID shipmentId);

    Item findItemById(UUID itemId);

    List<Item> findAllItemByShipmentId(UUID shipmentId);

    List<Item> findAllItem();

    Page<Item> pageAllItemByShipmentId(UUID shipmentId, Pageable pageable);

    Page<Item> pageAllItem(Pageable pageable);

    Item updateItem(UUID itemId, ItemRequestDTO itemRequestDTO);

    Item deleteItem(UUID itemId);

}
