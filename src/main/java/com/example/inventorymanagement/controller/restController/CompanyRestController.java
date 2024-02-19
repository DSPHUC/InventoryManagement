package com.example.inventorymanagement.controller.restController;

import com.example.inventorymanagement.service.company.ICompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
@AllArgsConstructor
public class CompanyRestController {
    @Autowired
    private ICompanyService companyService;

    @GetMapping
    public ResponseEntity<?> getAllCompany() {
        return new ResponseEntity<>(companyService.findAll(), HttpStatus.OK);
    }


}
