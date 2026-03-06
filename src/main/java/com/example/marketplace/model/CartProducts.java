package com.example.marketplace.model;
import jakarta.persistence.*;

@Entity
public class CartProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartProductsId;

    @ManyToOne(optional = false)
    private Cart cart;

    @ManyToOne(optional = false)
    private Product product;

    private int quantity;

    public Integer getCartProductsId() {
        return cartProductsId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}