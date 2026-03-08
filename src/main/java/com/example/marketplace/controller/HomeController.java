package com.example.marketplace.controller;
import com.example.marketplace.ProductFilter;
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
    public String home(ProductFilter filter, Model model) {
        model.addAttribute("products", productService.search(filter));
        model.addAttribute("filter", filter);

        return "home";
    }
}