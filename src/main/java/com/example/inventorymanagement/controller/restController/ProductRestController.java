package com.example.inventorymanagement.controller.restController;

import com.example.inventorymanagement.model.Product;
import com.example.inventorymanagement.service.product.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProduct() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
