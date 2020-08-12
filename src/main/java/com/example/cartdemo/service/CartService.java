package com.example.cartdemo.service;

import com.example.cartdemo.dto.CartDTO;

public interface CartService {

    CartDTO getProductsInCart(String username);

    void addToCart(String username, Long productId);

    void removeFromCart(String username, Long productId);

}
