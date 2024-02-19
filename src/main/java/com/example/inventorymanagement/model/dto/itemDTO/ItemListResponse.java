package com.example.inventorymanagement.model.dto.itemDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class ItemListResponse {
    private String product;
    private String warehouse;
    private String company;
    private BigDecimal stock;
    public ItemListResponse(String product, String company, String warehouse, BigDecimal stock) {
        this.product = product;
        this.company = company;
        this.warehouse = warehouse;
        this.stock = stock;
    }
}
