package com.example.springassessment2.controller;

import com.example.springassessment2.entity.*;
import com.example.springassessment2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller class for managing endpoints related to Tenants and associated entities.
 */
@RestController
@RequestMapping("/api")
public class MainController {

    UserService userService;

    AddressService addressService;

    ProductService productService;


    @Autowired
    public MainController(AddressService addressService, ProductService productService, UserService userService) {
        this.addressService = addressService;
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping("/users/get")
    public ResponseEntity<List<User>> getAllTenants() {
        List<User> tenants = userService.getAllUsers();
        return ResponseEntity.ok(tenants);
    }


    /**
     * Endpoint for saving a User entity.
     *
     * @param user the User entity to save
     * @return ResponseEntity indicating the success of the operation
     */
    @PostMapping("/users")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User saved successfully");
    }

    @PostMapping("/product/add")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok("Product saved successfully");
    }

    @PostMapping("product/update")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product.getId(), product);
        return ResponseEntity.ok("Product Updated Successfully");
    }

    @DeleteMapping("product/delete")
    public ResponseEntity<String> deleteProduct(@RequestBody UUID product_id) {
        productService.deleteProduct(product_id);
        return ResponseEntity.ok("Product Deleted Successfully");
    }

}