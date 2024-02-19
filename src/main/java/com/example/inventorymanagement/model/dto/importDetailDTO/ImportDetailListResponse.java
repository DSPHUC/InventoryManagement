package com.example.inventorymanagement.model.dto.importDetailDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ImportDetailListResponse {
    private Long id;
    private String productName;
    private BigDecimal quantity;
    private BigDecimal price;
    private String createAt;
    private String userCreate;

    public ImportDetailListResponse(Long id, String productName, BigDecimal quantity, BigDecimal price, String createAt, String userCreate) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.createAt = createAt;
        this.userCreate = userCreate;
    }
}
