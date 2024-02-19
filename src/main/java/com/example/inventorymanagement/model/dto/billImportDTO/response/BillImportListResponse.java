package com.example.inventorymanagement.model.dto.billImportDTO.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BillImportListResponse {
    private Long id;
    private String creator;
    private String createAt;
    private BigDecimal total;

    public BillImportListResponse(Long id, String creator, String createAt, BigDecimal total) {
        this.id = id;
        this.creator = creator;
        this.createAt = createAt;
        this.total = total;
    }
}
