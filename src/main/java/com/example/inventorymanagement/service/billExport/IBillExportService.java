package com.example.inventorymanagement.service.billExport;

import com.example.inventorymanagement.model.BillExport;
import com.example.inventorymanagement.model.dto.billExportDTO.BillExportRequest;
import com.example.inventorymanagement.service.IGeneralService;

public interface IBillExportService extends IGeneralService<BillExport,Long> {

    void create(BillExportRequest billExportRequest);
}
