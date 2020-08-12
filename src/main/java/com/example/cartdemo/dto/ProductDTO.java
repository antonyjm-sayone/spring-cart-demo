package com.example.cartdemo.dto;


import com.example.cartdemo.models.Product;

public class ProductDTO {

    private Long id;
    private String name;
    private int quantity;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.quantity = product.getQuantity();
    }

    public ProductDTO() {}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
