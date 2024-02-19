package com.example.inventorymanagement.service.billImport;

import com.example.inventorymanagement.model.BillImport;
import com.example.inventorymanagement.model.ImportDetail;
import com.example.inventorymanagement.model.dto.billImportDTO.BillImportRequest;
import com.example.inventorymanagement.model.dto.billImportDTO.response.BillImportListResponse;
import com.example.inventorymanagement.model.dto.importDetailDTO.ImportDetailListResponse;
import com.example.inventorymanagement.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBillImportService extends IGeneralService<BillImport,Long> {
    void create(BillImportRequest billImportRequest);

    List<ImportDetailListResponse> findAllImportDetailByIdBillImport(Long idBillImport);

    Page<BillImportListResponse> findAllBill(Pageable pageable, String search);
}
