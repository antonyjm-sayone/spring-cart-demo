package com.example.cartdemo.service;

import com.example.cartdemo.dto.ProductDTO;

import java.util.Set;

public interface ProductService {

    Set<ProductDTO> getProducts();
}
