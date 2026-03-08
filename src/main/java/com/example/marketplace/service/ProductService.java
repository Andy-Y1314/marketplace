package com.example.marketplace.service;

import com.example.marketplace.ProductFilter;
import com.example.marketplace.model.Product;
import com.example.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public List<Product> search(ProductFilter filter) {
        List<Product> products = productRepository.findAll().stream().filter(Product::isVisible).toList();

        if (filter.getGenre() != null && !filter.getGenre().isEmpty()) {
            products = products.stream().filter(p -> p.getGenre() != null && p.getGenre().toLowerCase().contains(filter.getGenre().toLowerCase())).toList();
        }

        if (filter.getAuthor() != null && !filter.getAuthor().isEmpty()) {
            products = products.stream().filter(p -> p.getAuthor() != null && p.getAuthor().toLowerCase().contains(filter.getAuthor().toLowerCase())).toList();
        }

        if (filter.getRating() != null) {
            products = products.stream().filter(p -> p.getRating() >= filter.getRating()).toList();
        }
        return products;
    }
}
