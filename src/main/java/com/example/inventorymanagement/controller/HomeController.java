package com.example.inventorymanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String check() {
        return "/products/home";
    }

    @GetMapping("/items")
    public String showItemListPageDashboard() {
        return "/products/home";
    }

    @GetMapping("/billImport")
    public String showBillImportListPageDashboard() {
        return "/bill/billImport/home";
    }

    @GetMapping("/billExport")
    public String showBillExportListPageDashboard() {
        return "/bill/billExport/home";
    }

}
