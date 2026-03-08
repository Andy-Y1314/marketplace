package com.example.marketplace.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShopOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopOrderId;

    @ManyToOne(optional = false)
    private ShopUser user;

    private String status = "PROCESSING";
    private double totalAmount;
    private LocalDateTime orderTime = LocalDateTime.now().withSecond(0).withNano(0);

    @OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShopOrderProducts> shopOrderProducts = new ArrayList<>();

    public Integer getShopOrderId() {
        return shopOrderId;
    }

    public ShopUser getUser() {
        return user;
    }

    public void setUser(ShopUser user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public List<ShopOrderProducts> getShopOrderProducts() {
        return shopOrderProducts;
    }

    public String getForMattedOrderTime() {
        return orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}