package com.example.cartdemo.controller;


import com.example.cartdemo.dto.CartDTO;
import com.example.cartdemo.dto.LoginParams;
import com.example.cartdemo.dto.ProductDTO;
import com.example.cartdemo.exceptions.NotInCartException;
import com.example.cartdemo.exceptions.NotInStockException;
import com.example.cartdemo.service.CartService;
import com.example.cartdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;
import org.springframework.http.HttpStatus;

@RestController
public class ShopController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // API to login user
    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginParams params) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(params.getUsername(),
                params.getPassword());
        try {
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    // API to list products
    @GetMapping(value="/products/list")
    public ResponseEntity getProductsList(){
        Set<ProductDTO> productDTOS = productService.getProducts();
        return new ResponseEntity(productDTOS, HttpStatus.OK);
    }

    // API to add product to cart
    @PostMapping(value="/product/{productId}/addToCart")
    public ResponseEntity addToCart(@PathVariable Long productId, Principal principal){
        String username = principal.getName();
        try {
            cartService.addToCart(username, productId);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch(NotInStockException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }

    // API to remove product from cart
    @PostMapping(value="/cart/removeFromCart/{productId}")
    public ResponseEntity removeFromCart(@PathVariable Long productId, Principal principal){
        String username = principal.getName();
        try {
            cartService.removeFromCart(username, productId);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (NotInCartException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    // API to list cart items
    @GetMapping(value="/cart/list")
    public ResponseEntity listCart(Principal principal){

        String username = principal.getName();
        CartDTO cartDTO = cartService.getProductsInCart(username);
        return new ResponseEntity(cartDTO, HttpStatus.OK);

    }

}
