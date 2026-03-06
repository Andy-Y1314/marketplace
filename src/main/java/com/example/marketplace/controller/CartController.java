package com.example.marketplace.controller;

import com.example.marketplace.model.CartProducts;
import com.example.marketplace.model.ShopUser;
import com.example.marketplace.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    private ShopUser getUser(HttpSession session) {
        return (ShopUser) session.getAttribute("user");
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        ShopUser user = getUser(session);
        if (user == null) return "redirect:/login";

        List<CartProducts> cartProducts = cartService.getCartProducts(user);
        model.addAttribute("cartProducts", cartProducts);
        model.addAttribute("total", cartService.calculateTotal(cartProducts));
        return "cart";
    }

    @PostMapping("/cart/add/{productId}")
    public String add(@PathVariable int productId, HttpSession session) {
        ShopUser user = getUser(session);
        if (user == null) return "redirect:/login";
        cartService.addProduct(user, productId);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update/{productId}")
    public String update(@PathVariable int productId, @RequestParam int quantity, HttpSession session) {
        ShopUser user = getUser(session);
        if (user == null) return "redirect:/login";
        cartService.updateQuantity(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{productId}")
    public String remove(@PathVariable int productId, HttpSession session) {
        ShopUser user = getUser(session);
        if (user == null) return "redirect:/login";
        cartService.removeProduct(user, productId);
        return "redirect:/cart";
    }
}
