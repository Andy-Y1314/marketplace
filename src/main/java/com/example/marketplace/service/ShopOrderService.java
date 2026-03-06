package com.example.marketplace.service;
import com.example.marketplace.model.*;
import com.example.marketplace.repository.ShopOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopOrderService {
    @Autowired
    private ShopOrderRepository shopOrderRepository;
    @Autowired
    private CartService cartService;

    public ShopOrder checkout(ShopUser user) {
        List<CartProducts> cartProducts = cartService.getCartProducts(user);
        if (cartProducts.isEmpty()) return null;

        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setUser(user);
        shopOrder.setStatus("PROCESSING");

        double total = 0;

        for (CartProducts cp : cartProducts) {
            Product p = cp.getProduct();

            ShopOrderProducts sop = new ShopOrderProducts();
            sop.setShopOrder(shopOrder);

            sop.setProductId(p.getProductId());
            sop.setProductName(p.getProductName());
            sop.setProductPrice(p.getProductPrice());
            sop.setQuantity(cp.getQuantity());
            sop.setImageURL(p.getImageURL());

            double subtotal = sop.getProductPrice() * sop.getQuantity();
            sop.setSubtotal(subtotal);

            shopOrder.getShopOrderProducts().add(sop);
            total += subtotal;
        }
        shopOrder.setTotalAmount(total);
        ShopOrder saved = shopOrderRepository.save(shopOrder);
        cartService.clearCart(user);
        return saved;
    }

    public List<ShopOrder> myOrders(ShopUser user) {
        return shopOrderRepository.findByUserIdOrderByOrderTimeDesc(user.getId());
    }

    public List<ShopOrder> allOrders() {
        return shopOrderRepository.findAll();
    }

    public ShopOrder getById(int shopOrderId) {
        return shopOrderRepository.findById(shopOrderId).orElse(null);
    }

    public void updateStatus(int shopOrderId, String status) {
        ShopOrder order = getById(shopOrderId);
        if (order == null) return;
        order.setStatus(status);
        shopOrderRepository.save(order);
    }
}