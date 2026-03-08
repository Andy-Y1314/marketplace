package com.example.marketplace.service;

import com.example.marketplace.model.ShopUser;
import com.example.marketplace.repository.ShopUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopUserService {
    @Autowired
    ShopUserRepository shopUserRepository;

    public void registerUser(ShopUser user) {
        shopUserRepository.save(user);
    }

    public ShopUser login(String username, String password) {
        ShopUser user = shopUserRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
