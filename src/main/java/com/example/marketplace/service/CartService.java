package com.example.marketplace.service;
import com.example.marketplace.model.Cart;
import com.example.marketplace.model.CartProducts;
import com.example.marketplace.model.Product;
import com.example.marketplace.model.ShopUser;
import com.example.marketplace.repository.CartProductsRepository;
import com.example.marketplace.repository.CartRepository;
import com.example.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartProductsRepository cartProductsRepository;
    @Autowired
    private ProductRepository productRepository;

    public Cart getOrCreateCart(ShopUser user) {
        Cart cart = cartRepository.findByUserId(user.getId());
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }
        return cart;
    }

    public List<CartProducts> getCartProducts(ShopUser user) {
        Cart cart = getOrCreateCart(user);
        return cartProductsRepository.findByCartCartId(cart.getCartId());
    }

    public void addProduct(ShopUser user, int productId) {
        Cart cart = getOrCreateCart(user);
        CartProducts existingCart = cartProductsRepository.findByCartCartIdAndProductProductId(cart.getCartId(), productId);

        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            cartProductsRepository.save(existingCart);
            return;
        }

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null) return;

        CartProducts cp = new CartProducts();
        cp.setCart(cart);
        cp.setProduct(product);
        cp.setQuantity(1);
        cartProductsRepository.save(cp);
    }

    public void updateQuantity(ShopUser user, int productId, int quantity) {
        Cart cart = getOrCreateCart(user);
        CartProducts cp = cartProductsRepository.findByCartCartIdAndProductProductId(cart.getCartId(), productId);
        if (cp == null) return;
        if (quantity == 0) {
            cartProductsRepository.delete(cp);
        } else {
            cp.setQuantity(quantity);
            cartProductsRepository.save(cp);
        }
    }

    public void removeProduct(ShopUser user, int productId) {
        Cart cart = getOrCreateCart(user);
        CartProducts cp = cartProductsRepository.findByCartCartIdAndProductProductId(cart.getCartId(), productId);
        if (cp != null) cartProductsRepository.delete(cp);
    }

    public void clearCart(ShopUser user) {
        Cart cart = getOrCreateCart(user);
        List<CartProducts> all = cartProductsRepository.findByCartCartId(cart.getCartId());
        cartProductsRepository.deleteAll(all);
    }

    public double calculateTotal(List<CartProducts> cartProducts) {
        double total = 0;
        for (CartProducts cp : cartProducts) {
            total += cp.getProduct().getProductPrice() * cp.getQuantity();
        }
        return total;
    }
}