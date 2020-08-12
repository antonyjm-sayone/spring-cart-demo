package com.example.cartdemo.repository;

import com.example.cartdemo.models.Cart;
import com.example.cartdemo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    public Optional<Cart> findByUser(User user);
}
