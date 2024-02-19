package com.example.inventorymanagement.controller.restController;

import com.example.inventorymanagement.model.dto.billExportDTO.BillExportRequest;
import com.example.inventorymanagement.model.dto.billExportDTO.response.BillExportListResponse;
import com.example.inventorymanagement.model.dto.billImportDTO.response.BillImportListResponse;
import com.example.inventorymanagement.service.billExport.BillExportServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billExports")
@AllArgsConstructor
public class BillExportRestController {
    @Autowired
    private BillExportServiceImpl billExportService;

    @PostMapping
    public ResponseEntity<?> createExport(@RequestBody BillExportRequest billExportRequest) {
        billExportService.create(billExportRequest);
        return null;

    }

    @GetMapping
    public ResponseEntity<Page<BillExportListResponse>> getAllBillImport(@PageableDefault(size = 5) Pageable pageable,
                                                                         @RequestParam(defaultValue = "") String search) {
        Page<BillExportListResponse> billExportListResponses = billExportService.findAllBill(pageable, search);
        return new ResponseEntity<>(billExportListResponses, HttpStatus.OK);
    }
}
