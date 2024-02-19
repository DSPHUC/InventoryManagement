package com.example.inventorymanagement.controller.restController;

import com.example.inventorymanagement.model.dto.itemDTO.ItemListResponse;
import com.example.inventorymanagement.service.item.IItemService;
import com.example.inventorymanagement.service.item.ItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@AllArgsConstructor
public class ItemRestController {
    @Autowired
    private IItemService itemService;

    @GetMapping
    public ResponseEntity<Page<ItemListResponse>> getAllItem(@PageableDefault(size = 5) Pageable pageable,
                                                             @RequestParam(defaultValue = "") String search) {
        Page<ItemListResponse> itemListResponse = itemService.findAllItemPage(pageable, search);
        return new ResponseEntity<>(itemListResponse, HttpStatus.OK);
    }


}
