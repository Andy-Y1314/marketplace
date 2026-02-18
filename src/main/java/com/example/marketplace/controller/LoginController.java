package com.example.marketplace.controller;

import com.example.marketplace.model.ShopUser;
import com.example.marketplace.service.ShopUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private ShopUserService shopUserService;

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new ShopUser());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") ShopUser user, Model model, HttpSession session) {
        ShopUser currentUser = shopUserService.login(user.getUsername(), user.getPassword());
        if (currentUser != null) {
            session.setAttribute("user", currentUser);
            return "redirect:/";
        }
        model.addAttribute("loginError", "Invalid username or password");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new ShopUser());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") ShopUser user) {
        shopUserService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
