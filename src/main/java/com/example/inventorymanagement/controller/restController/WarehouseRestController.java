package com.example.inventorymanagement.controller.restController;

import com.example.inventorymanagement.service.warehouse.IWarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouses")
@AllArgsConstructor
public class WarehouseRestController {
    @Autowired
    private IWarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<?> getAllWarehouse() {
        return new ResponseEntity<>(warehouseService.findAll(), HttpStatus.OK);
    }
}
