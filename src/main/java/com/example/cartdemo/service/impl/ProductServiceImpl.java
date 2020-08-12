package com.example.cartdemo.service.impl;

import com.example.cartdemo.dto.ProductDTO;
import com.example.cartdemo.models.Product;
import com.example.cartdemo.repository.ProductRepository;
import com.example.cartdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Set<ProductDTO> getProducts(){
        List<Product> products = productRepository.findAll();
        Set<ProductDTO> productDTOs = new HashSet<>();
        for( Product product : products){
            productDTOs.add(new ProductDTO(product));
        }

        return productDTOs;
    }
}
