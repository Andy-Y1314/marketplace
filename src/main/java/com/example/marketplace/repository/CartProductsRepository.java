package com.example.marketplace.repository;
import com.example.marketplace.model.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartProductsRepository extends JpaRepository<CartProducts, Integer> {
    List<CartProducts> findByCartCartId(int cartId);
    CartProducts findByCartCartIdAndProductProductId(int cartId, int productId);
}
