package com.example.marketplace.repository;
import com.example.marketplace.model.ShopOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ShopOrderRepository extends JpaRepository<ShopOrder, Integer> {
    List<ShopOrder> findByUserIdOrderByOrderTimeDesc(int userId);
}