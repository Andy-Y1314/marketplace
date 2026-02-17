package com.example.marketplace.repository;

import com.example.marketplace.model.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopUserRepository extends JpaRepository<ShopUser, Integer> {
    ShopUser findByUsername(String username);
}
