package com.example.marketplace.repository;
import com.example.marketplace.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserId(int userId);
}
