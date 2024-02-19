package com.example.inventorymanagement.model.dto.billImportDTO;

import com.example.inventorymanagement.model.dto.productDTO.ProductImportDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillImportRequest {
    private String idUser;
    private List<ProductImportDetailRequest> productImportDetailRequests;
}
