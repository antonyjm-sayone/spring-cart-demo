package com.example.cartdemo.dto;

import com.example.cartdemo.models.Cart;

import java.util.List;

public class CartDTO {

    private Long id;
    private String username;
    private List<ProductDTO> products;

    public CartDTO(Cart cart, List<ProductDTO> productDTOS){
        this.id = cart.getId();
        this.username = cart.getUser().getUsername();
        this.products = productDTOS;
    }

    CartDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
