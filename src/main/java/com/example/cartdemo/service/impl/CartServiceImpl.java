package com.example.cartdemo.service.impl;

import com.example.cartdemo.dto.CartDTO;
import com.example.cartdemo.dto.ProductDTO;
import com.example.cartdemo.exceptions.NotFoundException;
import com.example.cartdemo.exceptions.NotInCartException;
import com.example.cartdemo.exceptions.NotInStockException;
import com.example.cartdemo.models.Cart;
import com.example.cartdemo.models.Product;
import com.example.cartdemo.models.User;
import com.example.cartdemo.repository.CartRepository;
import com.example.cartdemo.repository.ProductRepository;
import com.example.cartdemo.repository.UserRepository;
import com.example.cartdemo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get products in cart
    public CartDTO getProductsInCart(String username){
        // Get user and cart
        User user = userRepository.findByUsername(username).get();
        Cart cart = cartRepository.findByUser(user).get();

        // get cart products
        List<Product> products = cart.getProducts();

        // convert products to dtos
        List<ProductDTO> productDTOs = new ArrayList<>();
        for(Product product: products){
            productDTOs.add(new ProductDTO(product));
        }
        return new CartDTO(cart, productDTOs);
    }

    public void addToCart(String username, Long productId){
        // get product
        Optional<Product> optionalProduct = productRepository.findById(productId);
        // if product exists
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            // check if product in stock
            if (product.getQuantity() < 1) throw new NotInStockException();

            // update user cart
            User user = userRepository.findByUsername(username).get();
            Cart cart = user.getCart();
            cart.getProducts().add(product);
            cartRepository.save(cart);

            // reduce product stock
            product.setQuantity(product.getQuantity() - 1);
            productRepository.save(product);
        }
        else{
            throw  new NotFoundException();
        }
    }

    // remove product from cart
    public void removeFromCart(String username, Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        // if product exists
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();

            // get user cart
            User user = userRepository.findByUsername(username).get();
            Cart cart = user.getCart();

            // if product in cart
            if(cart.getProducts().contains(product)) {
                // remove product from cart
                cart.getProducts().remove(product);
                cartRepository.save(cart);

                // update product stock
                product.setQuantity(product.getQuantity() + 1);
                productRepository.save(product);
            }
            else{
                throw new NotInCartException();
            }
        }
        else{
            throw  new NotFoundException();
        }
    }
}
