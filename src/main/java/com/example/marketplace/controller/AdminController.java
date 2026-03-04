package com.example.marketplace.controller;

import com.example.marketplace.model.Product;
import com.example.marketplace.model.ShopUser;
import com.example.marketplace.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    private boolean isAdmin(HttpSession session) {
        ShopUser user = (ShopUser) session.getAttribute("user");
        return user != null && user.isAdmin();
    }

    @GetMapping("/products")
    public String adminPage(HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/";
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("product", new Product());
        return "admin-product";
    }

    @PostMapping("/products/save")
    public String saveProduct(@ModelAttribute Product product, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/";
        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Integer id, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/";
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable Integer id, HttpSession session, Model model) {
        if (!isAdmin(session)) return "redirect:/";
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("products", productService.getAllProducts());
        return "admin-product";
    }


    @GetMapping("/customers")
    public String adminCustomer(HttpSession session) {
        if (!isAdmin(session)) return "redirect:/";
        return "admin-customer-order";
    }
}
