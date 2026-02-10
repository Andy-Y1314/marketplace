package com.example.marketplace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin/products")
    public String adminProduct() {
        return "admin-product";
    }

    @GetMapping("/admin/customers")
    public String adminCustomer() {
        return "admin-customer-order";
    }
}
