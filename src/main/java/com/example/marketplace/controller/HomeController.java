package com.example.marketplace.controller;
import com.example.marketplace.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "home";
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        productService.addProduct();
        return "redirect:/";
    }
}