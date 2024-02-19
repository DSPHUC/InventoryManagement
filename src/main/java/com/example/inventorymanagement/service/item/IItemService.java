package com.example.inventorymanagement.service.item;

import com.example.inventorymanagement.model.Item;
import com.example.inventorymanagement.model.dto.itemDTO.ItemListResponse;
import com.example.inventorymanagement.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IItemService extends IGeneralService<Item,Long> {

    Page<ItemListResponse> findAllItemPage(Pageable pageable, String search);
}
