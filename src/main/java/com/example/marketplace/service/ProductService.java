package com.example.marketplace.service;

import com.example.marketplace.model.Product;
import com.example.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void addProduct() {
        Product product = new Product();
        product.setProductName("Harry Potter");
        product.setProductPrice(8.3);
        productRepository.save(product);
    }

}
