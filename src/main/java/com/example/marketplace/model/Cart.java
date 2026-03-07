package com.example.marketplace.model;
import jakarta.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @OneToOne(optional = false)
    private ShopUser user;

    public Integer getCartId() {
        return cartId;
    }

    public ShopUser getUser() {
        return user;
    }

    public void setUser(ShopUser user) {
        this.user = user;
    }
}