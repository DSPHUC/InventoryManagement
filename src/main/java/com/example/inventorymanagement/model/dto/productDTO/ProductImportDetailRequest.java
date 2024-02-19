package com.example.inventorymanagement.model.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImportDetailRequest {
    private Long productId;

    private BigDecimal quantity;
    private BigDecimal price;

    private Long companyId;

    private Long warehouseId;
}
