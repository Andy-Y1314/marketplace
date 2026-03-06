package com.example.marketplace.model;
import jakarta.persistence.*;

@Entity
public class ShopOrderProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shopOrderProductsId;

    @ManyToOne(optional = false)
    private ShopOrder shopOrder;

    private int productId;
    private String productName;
    private double productPrice;
    private int quantity;
    private double subtotal;
    private String imageURL;

    public Integer getShopOrderProductsId() {
        return shopOrderProductsId;
    }

    public ShopOrder getShopOrder() {
        return shopOrder;
    }

    public void setShopOrder(ShopOrder shopOrder) {
        this.shopOrder = shopOrder;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}