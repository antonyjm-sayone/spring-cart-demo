package com.example.cartdemo;

import com.example.cartdemo.models.Cart;
import com.example.cartdemo.models.Product;
import com.example.cartdemo.models.User;
import com.example.cartdemo.repository.CartRepository;
import com.example.cartdemo.repository.ProductRepository;
import com.example.cartdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DbInit implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String...args) throws Exception {
        User user = new User("user", "passw");
        userRepository.save(user);

        Product toy = new Product("Toy", 2);
        productRepository.save(toy);

        Product pencil = new Product("Pencil", 5);
        productRepository.save(pencil);

        Product soap = new Product("Soap", 10);
        productRepository.save(soap);

        Cart userCart = new Cart(user);
        cartRepository.save(userCart);
    }

}