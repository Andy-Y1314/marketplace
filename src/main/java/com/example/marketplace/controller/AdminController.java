package com.example.marketplace.controller;

import com.example.marketplace.model.Product;
import com.example.marketplace.model.ShopUser;
import com.example.marketplace.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {
    @Autowired
    private ProductService productService;

    private boolean isAdmin(HttpSession session) {
        ShopUser user = (ShopUser) session.getAttribute("user");
        return user != null && user.isAdmin();
    }

    @GetMapping
    public String adminPage(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/";
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", new Product());
        return "admin-product";
    }

    @GetMapping("/admin/customers")
    public String adminCustomer() {
        return "admin-customer-order";
    }
}
