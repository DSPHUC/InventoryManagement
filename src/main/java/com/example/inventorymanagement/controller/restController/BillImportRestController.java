package com.example.inventorymanagement.controller.restController;

import com.example.inventorymanagement.model.ImportDetail;
import com.example.inventorymanagement.model.dto.billImportDTO.BillImportRequest;
import com.example.inventorymanagement.model.dto.billImportDTO.response.BillImportListResponse;
import com.example.inventorymanagement.model.dto.importDetailDTO.ImportDetailListResponse;
import com.example.inventorymanagement.service.billImport.IBillImportService;
import com.example.inventorymanagement.service.importDetail.IImportDetailService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/billImports")
@AllArgsConstructor
public class BillImportRestController {
    @Autowired
    private final IBillImportService billImportService;

    @Autowired
    private final IImportDetailService importDetailService;

    @PostMapping
    public ResponseEntity<?> createBillImport(@RequestBody BillImportRequest billImportRequest) {
        billImportService.create(billImportRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<BillImportListResponse>> getAllBillImport(@PageableDefault(size = 5) Pageable pageable,
                                                                         @RequestParam(defaultValue = "") String search) {
        Page<BillImportListResponse> billImportListResponses = billImportService.findAllBill(pageable, search);
        return new ResponseEntity<>(billImportListResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    private ResponseEntity<List<ImportDetailListResponse>> getImportDetailById(@PathVariable Long id) {
      List<ImportDetailListResponse> list=  billImportService.findAllImportDetailByIdBillImport(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
