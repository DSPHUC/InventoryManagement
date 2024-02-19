package com.example.inventorymanagement.model.dto.billExportDTO;

import com.example.inventorymanagement.model.dto.productDTO.ProductExportDetailRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BillExportRequest {
    private String idUser;
    private List<ProductExportDetailRequest> productExportDetailRequests;
}
