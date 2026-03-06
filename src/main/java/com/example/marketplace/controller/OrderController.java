package com.example.marketplace.controller;
import com.example.marketplace.model.ShopOrder;
import com.example.marketplace.model.ShopUser;
import com.example.marketplace.service.ShopOrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    @Autowired
    private ShopOrderService shopOrderService;

    private ShopUser getUser(HttpSession session) {
        return (ShopUser) session.getAttribute("user");
    }

    @PostMapping("/orders/checkout")
    public String checkout(HttpSession session) {
        ShopUser user = getUser(session);
        if (user == null) return "redirect:/login";
        ShopOrder order = shopOrderService.checkout(user);
        if (order == null) return "redirect:/cart";
        return "redirect:/order/history";
    }

    @GetMapping("/order/history")
    public String history(HttpSession session, Model model) {
        ShopUser user = getUser(session);
        if (user == null) return "redirect:/login";
        model.addAttribute("orders", shopOrderService.myOrders(user));
        return "order-history";
    }
}
